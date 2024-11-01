    /*   
    Este código cria um obstáculo para um jogo estilo "Dino Game". Ele carrega 
    imagens de cactus para representar diferentes tipos de obstáculos. Cada 
    obstáculo se move para a esquerda em uma velocidade constante e, ao ser 
    desenhado na tela, usa uma das imagens carregadas. A classe também possui 
    métodos para obter a posição do obstáculo e seu limite, úteis para colisão 
    com o jogador.
    */
package dinogame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Obstacle {
    // Array de imagens para representar diferentes tipos de obstáculos
    private BufferedImage[] imagens;
    // Imagem atual do obstáculo que será desenhada na tela
    private BufferedImage imagemAtual;
    // Posição (x, y) do obstáculo e suas dimensões (largura e altura)
    private int x, y;
    private int width, height;
    // Velocidade com que o obstáculo se movimenta na tela
    private final int SPEED = 8;

    // Construtor que inicializa o obstáculo com uma posição inicial 'startX'
    public Obstacle(int startX) {
        x = startX;       // Define a posição inicial no eixo X
        y = 202;          // Define a posição no eixo Y (altura fixa para o obstáculo)
        width = 20;       // Largura do obstáculo
        height = 40;      // Altura do obstáculo
        
        // Inicializa o array de imagens e carrega cada uma das imagens de obstáculos
        imagens = new BufferedImage[6];
        try {
            // Carrega diferentes imagens de cactus que serão usadas para os obstáculos
            imagens[0] = ImageIO.read(new File("imagens/cactus1.png"));
            imagens[1] = ImageIO.read(new File("imagens/cactus2.png"));
            imagens[2] = ImageIO.read(new File("imagens/cactus3.png"));
            imagens[3] = ImageIO.read(new File("imagens/big-cactus1.png"));
            imagens[4] = ImageIO.read(new File("imagens/big-cactus2.png"));
            imagens[5] = ImageIO.read(new File("imagens/big-cactus3.png"));
        } catch (IOException e) {
            // Caso ocorra um erro ao carregar as imagens, exibe uma mensagem de erro
            System.out.println("Nao foi possivel carregar as imagens dos cactus");
            e.printStackTrace();
        }
        
        // Seleciona aleatoriamente uma das imagens carregadas para ser a imagem atual do obstáculo
        Random rand = new Random();
        imagemAtual = imagens[rand.nextInt(imagens.length)];
    }

    // Método que atualiza a posição do obstáculo, movendo-o para a esquerda
    public void update() {
        x -= SPEED;   // Reduz o valor de 'x' com base na velocidade, movimentando o obstáculo
    }

    // Método que desenha o obstáculo na tela, usando a imagem atual
    public void draw(Graphics g) {
        g.drawImage(imagemAtual, x, y, null);
    }

    // Método que retorna a posição atual do obstáculo no eixo X
    public int getX() {
        return x;
    }

    // Método que retorna a largura do obstáculo
    public int getWidth() {
        return width;
    }

    // Método que retorna um retângulo representando os limites do obstáculo,
    // usado para detectar colisões com outros objetos no jogo
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    

}




