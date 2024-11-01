
package dinogame; // Declara o pacote 'dinogame', que organiza classes relacionadas a esse jogo

import javax.swing.JFrame; // Importa a classe JFrame da biblioteca Swing, usada para criar a janela do jogo

// Classe principal do jogo, que herda de JFrame para criar uma janela
public class DinoGame extends JFrame {

    // Construtor da classe DinoGame, onde os elementos da janela são configurados
    public DinoGame() {
        setTitle("Jogo do Dinossauro"); // Define o título da janela como "Jogo do Dinossauro"
        setSize(1366, 768); // Define o tamanho da janela em 1366x768 pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a ação ao fechar a janela (encerra o programa)
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede que a janela seja redimensionada
        
        GamePanel gamePanel = new GamePanel(); // Cria uma nova instância de GamePanel, que contém o jogo em si
        add(gamePanel); // Adiciona o painel do jogo (GamePanel) à janela principal
        
        setVisible(true); // Torna a janela visível na tela
    }
    
    // Método principal do programa, ponto de entrada
    public static void main(String[] args) {
        new DinoGame(); // Cria uma nova instância de DinoGame, iniciando o jogo
    }
}


