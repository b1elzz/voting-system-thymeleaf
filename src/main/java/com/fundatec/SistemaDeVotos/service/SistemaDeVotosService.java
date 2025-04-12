package com.fundatec.SistemaDeVotos.service;

import com.fundatec.SistemaDeVotos.exception.FuncionarioJaVotouHojeException;
import com.fundatec.SistemaDeVotos.model.Funcionario;
import com.fundatec.SistemaDeVotos.model.Restaurante;
import com.fundatec.SistemaDeVotos.model.TotalVotosRestaurante;
import com.fundatec.SistemaDeVotos.model.Voto;
import com.fundatec.SistemaDeVotos.repository.FuncionarioRepository;
import com.fundatec.SistemaDeVotos.repository.RestauranteRepository;
import com.fundatec.SistemaDeVotos.repository.VotoRepository;
import com.fundatec.SistemaDeVotos.repository.VotoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço responsável pela lógica de negócio do sistema de votação.
 *
 * <p>Orquestra o fluxo de inserção de votos, incluindo a criação automática de funcionários
 * e restaurantes caso ainda não existam no banco. Também realiza a validação de votos repetidos
 * e disponibiliza a apuração dos resultados da votação atual.</p>
 */
@Service
public class SistemaDeVotosService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private VotoRepositoryImpl votoRepositoryImpl;

    /**
     * Realiza o processo completo de registro de um voto.
     *
     * <p>Valida se o funcionário já votou no dia atual e, se tudo estiver válido, persiste o voto.</p>
     *
     * @param funcionarioId ID do funcionário que está votando
     * @param restauranteId ID do restaurante escolhido
     * @throws FuncionarioJaVotouHojeException caso o funcionário já tenha votado no dia atual
     * @throws IllegalArgumentException caso o funcionário ou restaurante não sejam encontrados
     */
    public void inserirVoto(Integer funcionarioId, Integer restauranteId) throws FuncionarioJaVotouHojeException {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));

        verificarSeFuncionarioJaVotou(funcionario, getDataAtualZerada());
        Voto novoVoto = new Voto(getDataAtualZerada(), funcionario, restaurante);
        votoRepository.save(novoVoto);
    }

    /**
     * Cadastra um novo funcionário se ele ainda não existir.
     *
     * @param nomeFuncionario nome do funcionário
     * @return uma instância de {@link Funcionario} existente ou recém-criada
     */
    public Funcionario cadastrarFuncionario(String nomeFuncionario) {
        Funcionario funcionario = funcionarioRepository.findByNomeFuncionario(nomeFuncionario);
        if (funcionario == null) {
            funcionario = new Funcionario();
            funcionario.setNomeFuncionario(nomeFuncionario);
            funcionario = funcionarioRepository.save(funcionario);
        }
        return funcionario;
    }

    /**
     * Cadastra um novo restaurante se ele ainda não existir.
     *
     * @param nomeRestaurante nome do restaurante
     * @return uma instância de {@link Restaurante} existente ou recém-criada
     */
    public Restaurante cadastrarRestaurante(String nomeRestaurante) {
        Restaurante restaurante = restauranteRepository.findByNomeRestaurante(nomeRestaurante);
        if (restaurante == null) {
            restaurante = new Restaurante();
            restaurante.setNomeRestaurante(nomeRestaurante);
            restaurante = restauranteRepository.save(restaurante);
        }
        return restaurante;
    }

    /**
     * Lista todos os funcionários cadastrados.
     *
     * @return lista de {@link Funcionario}
     */
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    /**
     * Lista todos os restaurantes cadastrados.
     *
     * @return lista de {@link Restaurante}
     */
    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    /**
     * Valida se o funcionário já registrou voto na data atual.
     *
     * @param funcionario funcionário a ser verificado
     * @param data data da votação
     * @throws FuncionarioJaVotouHojeException se o funcionário já votou hoje
     */
    private void verificarSeFuncionarioJaVotou(Funcionario funcionario, Calendar data) throws FuncionarioJaVotouHojeException {
        boolean jaVotou = votoRepositoryImpl.votoPorData(funcionario.getId(), data);
        if (jaVotou) {
            throw new FuncionarioJaVotouHojeException(
                    String.format("O funcionário '%s' já votou hoje!", funcionario.getNomeFuncionario())
            );
        }
    }

    /**
     * Retorna a data atual com horas, minutos e segundos zerados.
     *
     * @return instância de {@link Calendar} representando a data de hoje
     */
    private Calendar getDataAtualZerada() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        return data;
    }

    /**
     * Apura os votos registrados no dia atual, agrupados por restaurante.
     *
     * @return lista de {@link TotalVotosRestaurante} representando os resultados da votação
     */
    public List<TotalVotosRestaurante> apuracaoVotos() {
        return votoRepositoryImpl.buscar();
    }
}