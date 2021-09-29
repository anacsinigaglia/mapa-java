package com.unicesumar.mapa.controle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.unicesumar.mapa.exceptions.DadoConsultadoException;
import com.unicesumar.mapa.modelo.Projeto;

public class ProjetoImpl implements ProjetoDAO {
    private static Set<Projeto> projetos = new HashSet<>();

    @Override
    public void adicionar(Projeto projeto) throws DadoConsultadoException {
        for (Projeto projeto1 : projetos) {
            if (projeto.getNome().equalsIgnoreCase(projeto1.getNome())) {
                throw new DadoConsultadoException("Projeto com o nome fornecido já existe.");
            }
        }

        projetos.add(projeto);
    }

    @Override
    public List<Projeto> listar() {
        List<Projeto> projetoList = new ArrayList<>();
        projetoList.addAll(projetos);
        return projetoList;
    }

    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for (Projeto projeto : projetos) {
            if (projeto.getNome().equalsIgnoreCase(nome)) {
                return projeto;
            }
        }
        throw new DadoConsultadoException("Projeto com o nome '" + nome + "' não foi encontrado.");
    }

    @Override
    public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
        Projeto projetoEncontrado = consultarPorNome(nome);
        projetoEncontrado.setNome(projeto.getNome());
        projetoEncontrado.setDataFinal(projeto.getDataFinal());
        return projetoEncontrado;
    }

    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
        if (projetos.contains(projeto)) {
            projetos.remove(projeto);
            return;
        }
        throw new DadoConsultadoException("Projeto com o nome '" + projeto.getNome() + "' não foi encontrado.");
    }

    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projeto = consultarPorNome(nome);
        this.excluir(projeto);
    }
}
