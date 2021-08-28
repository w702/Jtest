import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test extends JPanel implements KeyListener{
    int x = 300; // 円の描画位置（x座標）
    int y = 200; // 円の描画位置（y座標）
	int dx = 10;
	int dy = 10;
    int my = 150;
    int ty = 150;

    // コンストラクタ（初期化処理）
    public test() {
        setPreferredSize(new Dimension(600,400));
		// スレッドの生成
		Thread th = new AnimeThread();
		th.start();
        setFocusable(true);
        // キーリスナーを登録（忘れやすい）
        addKeyListener(this);
    }
    // 画面描画
    public void paintComponent(Graphics g) {
        // 背景を黒にする
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 400);
        g.setColor(Color.white);
        g.fillRect(0, my, 20, 100);
        g.setColor(Color.white);
        g.fillRect(580, ty, 20, 100);
        // 円を描く
        g.setColor(Color.yellow);
        g.fillOval(x, y, 50, 50);
        
    }
    public void keyTyped(KeyEvent e) {
		//使用しないので空にしておきます。
	}
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP :
                // 上キーが押されたらボールを上に移動
                my -= 30;
                break;
            case KeyEvent.VK_DOWN :
                // 上キーが押されたらボールを上に移動
                my += 30;
                break;
        }
        // ボールを移動したので再描画
        // 忘れやすいので注意
        repaint();
    }
    public void keyReleased(KeyEvent e) {
	}
    // スレッド
    class AnimeThread extends Thread {
        public void run() {
            while(true) {
                // 右にちょっと動かす
                x = x + dx;
                y = y + dy;
				// はみ出たら左端に
				if (x == 530) {
					dx = -dx;
				}
				if (x > 530) {
					System.out.printf("YOU_WIN!!");
				}
				if (x == 20) {
                    if(my < y && y < my + 100){
					    dx = -dx;
                    }
				}
				if (x < 20) {
					System.out.printf("GAME_OVER!!");
				}
				if (y > 350) {
					dy = -dy;
				}
				if (y < 0) {
					dy = -dy;
				}
                if(ty > y){
                    ty -= 10;
                }
                if(ty < y){
                    ty += 10;
                }
                // 再描画要求
                repaint();
                // 50ms(0.05秒)待機
                try {
                    Thread.sleep(50);
                } catch(Exception e) {
                }
            }
        }
    }

    // 起動時
    public static void main(String[] args) {
        // ウィンドウの作成と表示
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(new test());
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }
}
