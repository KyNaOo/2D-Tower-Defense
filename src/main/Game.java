package src.main;

import src.Inputs.KeyboardListener;
import src.Inputs.MyMouseListener;
import src.Scenes.Menu;
import src.Scenes.Playing;
import src.Scenes.Settings;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{

    private GameScreen gameScreen;
    private long lastTime;
    private Thread gameThread;
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;
    private Render render;

    private Menu menu;
    private Playing playing;
    private Settings settings;
    public Game() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initClasses();

        setResizable(false);
        add(gameScreen);
        pack();
        setVisible(true);
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen=new GameScreen(this);
        this.menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    private void initInput(){
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }
   

    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.initInput();
        game.start();
    }


    public Render getRender() {
        return render;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastTimeCheck = System.currentTimeMillis();

        long now;
        while (true){
            now = System.nanoTime();
            if(now-lastFrame >= timePerFrame){
                lastFrame = now;
                repaint();
                frames++;
            }
            if (now - lastUpdate >= timePerUpdate){
                lastUpdate = now;
                updates++;
            }
            if(System.currentTimeMillis() - lastTimeCheck>=1000){
                System.out.println("fps = "+ frames + " | ups = "+ updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }

    }
}
