
package dinogame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dino {
    private BufferedImage[] imagens; // Array para armazenar as imagens da animação do Dino
    private int imagemAtual; // Índice da imagem atual na animação
    private int x, y; // Posições X e Y do Dino na tela
    private int width, height; // Largura e altura do Dino
    private int yVelocity; // Velocidade vertical do Dino
    private boolean jumping; // Define se o Dino está pulando ou não
    private final int GRAVITY = 1; // Força da gravidade que afeta o Dino enquanto ele está pulando
    private final int JUMP_STRENGTH = -15; // Força do pulo do Dino
    private int frameDelay; // Contador para controlar o atraso entre frames da animação
    private final int FRAME_DELAY_MAX = 10; // Intervalo para trocar as imagens na animação (ajustável para alterar a velocidade da animação)

    // Construtor da classe Dino
    public Dino() {
        x = 50; // Posição X inicial do Dino
        y = 180; // Posição Y inicial do Dino
        width = 40; // Largura do Dino
        height = 40; // Altura do Dino
        yVelocity = 0; // Velocidade vertical inicial
        jumping = false; // Define que o Dino não está pulando inicialmente
        imagemAtual = 0; // Imagem inicial da animação
        frameDelay = 0; // Reseta o contador de atraso para a animação
        carregarImagens(); // Chama o método para carregar as imagens da animação

    }

    // Método para carregar as imagens da animação
    private void carregarImagens() {
        try {
            imagens = new BufferedImage[3]; // Array para três imagens de animação
            imagens[0] = ImageIO.read(new File("imagens/dino-run1.png")); // Carrega a primeira imagem
            imagens[1] = ImageIO.read(new File("imagens/dino-run2.png")); // Carrega a segunda imagem
            imagens[2] = ImageIO.read(new File("imagens/dino-run3.png")); // Carrega a terceira imagem
        } catch (IOException e) {
            System.out.println("Nao foi possivel carregar as imagens do dino"); // Mensagem de erro caso o carregamento falhe
            e.printStackTrace();
        }
    }

    // Método para atualizar o estado do Dino
    public void update() {
        // Se o Dino não estiver pulando, atualiza a animação
        if (!jumping) {
            frameDelay++; // Incrementa o contador de atraso entre frames
            if (frameDelay >= FRAME_DELAY_MAX) { // Verifica se é hora de trocar de imagem
                frameDelay = 7; // Reseta o atraso
                imagemAtual = (imagemAtual + 1) % imagens.length; // Alterna entre as imagens da animação
            }
        }

        // Se o Dino estiver pulando, atualiza a posição vertical
        if (jumping) {
            yVelocity += GRAVITY; // Aplica a gravidade na velocidade vertical
            y += yVelocity; // Atualiza a posição Y do Dino com base na velocidade vertical

            // Verifica se o Dino chegou ao chão
            if (y >= 180) { 
                y = 180; // Reposiciona o Dino no chão
                jumping = false; // Interrompe o pulo
                yVelocity = 0; // Reseta a velocidade vertical
            }
        }
    }

    // Método para fazer o Dino pular
    public void jump() {
        if (!jumping) { // Se o Dino não estiver pulando
            yVelocity = JUMP_STRENGTH; // Define a força do pulo
            jumping = true; // Define que o Dino está pulando
        }
    }
    
    // Método para desenhar o Dino na tela
    public void draw(Graphics g) {
        g.drawImage(imagens[imagemAtual], x, y, null); // Desenha a imagem atual do Dino na posição X e Y
    }

    // Método para obter o retângulo de colisão do Dino
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Retorna um retângulo nas posições e dimensões do Dino
    }
}




