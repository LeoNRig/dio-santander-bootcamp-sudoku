package uicustom.panel;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uicustom.button.FinishGameButton;
import uicustom.button.ResetButton;
import uicustom.button.buttonGameCheckStatus;
import service.BoardService;
import uicustom.frame.MainFrame;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static models.GameStatusEnum.COMPLETE;
import static models.GameStatusEnum.INCOMPLETE;
import static models.GameStatusEnum.NON_STARTED;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600,600);

    private final BoardService boardService;

    private JButton resetGameButton;
    private JButton checkGamesStatus;
    private JButton finishGameButton;

    public MainScreen(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        addFinishGameButton(mainPanel);
        addShowGameStatusButton(mainPanel);
        addResetButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
                        
    private void addResetButton(final JPanel mainPanel) {
        resetGameButton = new ResetButton(e ->{
            var dialogResult = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente reiniciar o jogo",
                "Limpar o jogo",
                YES_NO_OPTION,
                QUESTION_MESSAGE
            );
            if(dialogResult == 0){
                boardService.reset();
            }   
        });
        mainPanel.add(resetGameButton);
    }
        
    private void addShowGameStatusButton(final JPanel mainPanel) {
        checkGamesStatus = new buttonGameCheckStatus(e ->{
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch(gameStatus){
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está incompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors ? "e contém erros" : "e não contém erros";
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(checkGamesStatus);
    }
        
    private void addFinishGameButton(final JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e ->{
            if (boardService.gameIsFinished()) {
                JOptionPane.showMessageDialog(null,"Parabens você concluiu");
                resetGameButton.setEnabled(false);
                checkGamesStatus.setEnabled(false);
                finishGameButton.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null,"Seu jogo tem alguma incosistência");
            }
        });
        mainPanel.add(finishGameButton);
    }

}
