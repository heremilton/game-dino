package dinogame;
// Importa bibliotecas necessárias para o funcionamento do jogo e para o áudio
import javax.swing.JPanel;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;

// Classe principal do painel do jogo
public class GamePanel extends JPanel implements ActionListener {

    // Declara o plano de fundo do jogo
    private PlanoDeFundo fundo;

    // Declara o personagem Dino e uma lista de obstáculos
    private Dino dino;
    private List<Obstacle> obstacles;

    // Declara o timer para controle do tempo, uma variável para pontuação e um booleano para indicar fim de jogo
    private Timer timer;
    private boolean gameOver;
    private int score;  // Variável para a pontuação do jogo

    // Construtor da classe GamePanel
    public GamePanel() {

        // Inicializa o plano de fundo
        fundo = new PlanoDeFundo();

        // Define a cor de fundo do painel para branco
        setBackground(Color.WHITE);

        // Inicializa o personagem Dino e a lista de obstáculos
        dino = new Dino();
        obstacles = new ArrayList<>();

        // Inicializa a pontuação
        score = 0;

        // Configura o timer para atualizar o jogo a cada 16ms (aproximadamente 60 FPS)
        timer = new Timer(16, this); // Atualiza a cada 16ms
        timer.start();

        // Adiciona um listener de teclado para detectar a tecla de espaço
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Quando a tecla de espaço é pressionada e o jogo não terminou, o Dino pula
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
                    dino.jump();
                    playJumpSound(); // Toca o som do pulo
                }
            }
        });

        // Define que o painel pode receber foco (necessário para detectar teclas)
        setFocusable(true);
    }

    // Método para reproduzir o som do pulo
    private void playJumpSound() {
        try {
            // Caminho para o arquivo de som
            File soundFile = new File("sounds/jump.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Método para reproduzir o som do pulo
    private void playDeathSound() {
        try {
            // Caminho para o arquivo de som
            File soundFile = new File("sounds/death.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Método para desenhar os componentes do jogo na tela
    @Override
    protected void paintComponent(Graphics g2) {
        super.paintComponent(g2);

        // Cria um objeto Graphics2D para desenhar os elementos do jogo
        Graphics2D g = (Graphics2D) g2.create();

        // Desenha o plano de fundo
        fundo.paint(g);

        // Desenha o personagem Dino
        dino.draw(g);

        // Desenha cada obstáculo presente na lista de obstáculos
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
       
            // Exibe a pontuação na tela
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Pontuação: " + score, 10, 20);

    }

    // Método chamado a cada atualização do timer (cada 16ms)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Atualiza o estado do Dino e dos obstáculos
            dino.update();
            spawnObstacles();
            updateObstacles();
            checkCollisions();

            // Incrementa a pontuação enquanto o jogo não terminou
            score++;
        }
        // Redesenha o painel para mostrar as mudanças
        repaint();
    }

    // Método para adicionar novos obstáculos conforme necessário
    private void spawnObstacles() {
        // Adiciona um novo obstáculo se a lista estiver vazia ou o último obstáculo estiver longe o suficiente
        if (obstacles.isEmpty() || obstacles.get(obstacles.size() - 1).getX() < 600) {
            obstacles.add(new Obstacle(getWidth()));
        }
    }

    // Método para atualizar a posição dos obstáculos e remover os que saíram da tela
    private void updateObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            obstacle.update();

            // Remove obstáculos que saíram completamente da tela (fora do lado esquerdo)
            if (obstacle.getX() < -obstacle.getWidth()) {
                obstacles.remove(i);
                i--; // Ajusta o índice após a remoção
            }
        }
    }

    // Método para verificar se houve colisão entre o Dino e algum obstáculo
    private void checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            // Se o Dino colide com um obstáculo, o jogo termina
            if (dino.getBounds().intersects(obstacle.getBounds())) {
                gameOver = true;
                timer.stop(); // Para o timer
                playDeathSound();
                // Exibe uma mensagem de Game Over e pergunta se o jogador quer reiniciar
                int resposta = JOptionPane.showConfirmDialog(this, "Você perdeu!!! Pontuação: " + score + "\nQuer jogar novamente?");
                if (resposta == JOptionPane.OK_OPTION) {
                    resetGame(); // Reinicia o jogo
                } else {
                    System.exit(0); // Fecha o jogo se o jogador não quiser jogar novamente
                }
            }
        }
    }

    // Método para reiniciar o jogo no estado inicial
    private void resetGame() {
        dino = new Dino(); // Cria um novo Dino
        obstacles.clear(); // Limpa a lista de obstáculos
        score = 0; // Reinicia a pontuação
        gameOver = false; // Redefine o estado de Game Over
        timer.start(); // Reinicia o timer
    }
}

