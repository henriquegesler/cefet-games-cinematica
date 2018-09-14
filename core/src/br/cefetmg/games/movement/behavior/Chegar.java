package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'a';
    private static float timeToTarget = (float) 0.25;
    private float radius;

    public Chegar(float maxVelocidade) {
        this(NOME, random(maxVelocidade-20,maxVelocidade));
    }

    protected Chegar(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
        this.radius = 30;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();
        output.velocidade.x = alvo.getObjetivo().x - agente.posicao.x;
        output.velocidade.y = alvo.getObjetivo().y - agente.posicao.y;
        if(output.velocidade.len() < this.radius){
            output.velocidade.x = 0;
            output.velocidade.y = 0;
            return output;
        }
        //timeToTarget = 1/timeToTarget;
        output.velocidade.scl(timeToTarget);
        output.velocidade.scl(maxVelocidade);
        if(output.velocidade.len() > maxVelocidade){
            output.velocidade.nor();
            output.velocidade.scl(maxVelocidade);
        }
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        output.rotacao = 0;
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.A;
    }
}
