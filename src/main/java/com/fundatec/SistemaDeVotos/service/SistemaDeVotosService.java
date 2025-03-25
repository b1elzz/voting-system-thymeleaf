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
     * <p>Cria automaticamente o funcionário ou restaurante se não existirem, valida
     * se o funcionário já votou no dia atual e, se tudo estiver válido, persiste o voto.</p>
     *
     * @param nomeFuncionario nome do funcionário que está votando
     * @param nomeRestaurante nome do restaurante escolhido
     * @throws FuncionarioJaVotouHojeException caso o funcionário já tenha votado no dia atual
     */
    public void inserirVoto(String nomeFuncionario, String nomeRestaurante) throws FuncionarioJaVotouHojeException {
        Funcionario func = buscarOuCadastrarFuncionario(nomeFuncionario);
        Restaurante restaurante = buscarOuCadastrarRestaurante(nomeRestaurante);

        Voto novoVoto = new Voto(getDataAtualZerada(), func, restaurante);
        votoRepository.save(novoVoto);
    }

    /**
     * Busca um funcionário pelo nome. Caso não exista, cria e salva um novo.
     *
     * @param nomeFuncionario nome do funcionário
     * @return uma instância de {@link Funcionario} existente ou recém-criada
     */
    private Funcionario buscarOuCadastrarFuncionario(String nomeFuncionario) throws FuncionarioJaVotouHojeException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario(nomeFuncionario);

        Optional<Funcionario> funcionarioExistente = Optional.ofNullable(funcionarioRepository.findByNomeFuncionario(nomeFuncionario));

        if (funcionarioExistente.isPresent()) {
            funcionario = funcionarioExistente.get();
            verificarSeFuncionarioJaVotou(funcionario, getDataAtualZerada());
        }

        return funcionario;
    }

    /**
     * Busca um restaurante pelo nome. Caso não exista, cria e salva um novo.
     *
     * @param nomeRestaurante nome do restaurante
     * @return uma instância de {@link Restaurante} existente ou recém-criada
     */
    private Restaurante buscarOuCadastrarRestaurante(String nomeRestaurante) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNomeRestaurante(nomeRestaurante);

        Optional<Restaurante> restauranteExistente = Optional.ofNullable(restauranteRepository.findByNomeRestaurante(nomeRestaurante));
        if (restauranteExistente.isPresent()) {
            restaurante = restauranteExistente.get();
        }

        return restaurante;

    }

    /**
     * Valida se o funcionário já registrou voto na data atual.
     *
     * @param funcionario funcionário a ser verificado
     * @param data        data da votação
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
     *
     * @return instância de {@link Calendar} representando a data de hoje
     */
    private Calendar getDataAtualZerada() {

        return Calendar.getInstance();
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
