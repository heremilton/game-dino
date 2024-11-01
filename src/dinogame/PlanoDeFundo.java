/*
Construtor PlanoDeFundo: Inicializa a largura e altura da imagem e tenta carregar a imagem de fundo.
Método paint: Desenha duas instâncias da imagem lado a lado para dar continuidade visual e cria um 
efeito de rolagem para a esquerda. Quando a primeira imagem sai da tela, a posição é reiniciada para 
continuar o efeito.
*/
package dinogame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlanoDeFundo {
    // Variável para posição horizontal da imagem
    int x;
    // Armazena a imagem de fundo carregada
    BufferedImage imagem;
    
    // Define a largura e a altura da imagem de fundo
    private int largura;
    private int altura;

    // Construtor da classe PlanoDeFundo
    public PlanoDeFundo() {
        // Define a largura e a altura do plano de fundo
        largura = 1544;
        altura = 500;
        try {
            // Carrega a imagem de fundo do arquivo "fundo1.png" na pasta "imagens"
            imagem = ImageIO.read(new File("imagens/fundo1.png"));
            
            
            
        } catch (IOException e) {
            // Caso ocorra um erro ao carregar a imagem, exibe uma mensagem de erro
            System.out.println("Nao foi possivel carregar o plano de fundo");
            e.printStackTrace();
        }
        // Inicializa a posição x da imagem em 0
        x = 0;
    }
    
    // Método para desenhar o fundo na tela
    public void paint(Graphics2D g) {
        // Define a largura da tela onde a imagem será exibida (neste caso, 1366 pixels)
        int larguraTela = 1366;
        
        // Desenha a primeira imagem na posição definida por "x"
        g.drawImage(imagem, x, 0, null);
        // Desenha a segunda imagem logo após a primeira para criar o efeito de continuidade
        g.drawImage(imagem, x + imagem.getWidth(), 0, null);
        
        // Move a posição "x" para a esquerda para criar o efeito de rolagem
        x -= 8;
        
        // Reinicia a posição de "x" quando a primeira imagem sai completamente da tela
        if (x <= -imagem.getWidth()) {
            x = 0;
        }
    }
}

