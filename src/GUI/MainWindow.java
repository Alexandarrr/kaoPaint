package GUI;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import geometrija.Duz;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class MainWindow {

	private JFrame frmKaoPaint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.frmKaoPaint.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKaoPaint = new JFrame();
		frmKaoPaint.setTitle("kao Paint :)");
		frmKaoPaint.setBounds(100, 100, 450, 300);
		frmKaoPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmKaoPaint.setJMenuBar(menuBar);
		frmKaoPaint.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblStatus = new JLabel("New label");
		frmKaoPaint.getContentPane().add(lblStatus, BorderLayout.SOUTH);
		
		JPanel pnlZaAlate = new JPanel();
		frmKaoPaint.getContentPane().add(pnlZaAlate, BorderLayout.WEST);
		

		JToggleButton tglbtnCrtajDuz = new JToggleButton("Crtanje duzi");
		tglbtnCrtajDuz.setSelected(true);
		pnlZaAlate.add(tglbtnCrtajDuz);
		
		JToggleButton tglbtnCrtajKrug = new JToggleButton("Crtanje kruga");
		pnlZaAlate.add(tglbtnCrtajKrug);
		
		PanelZaCrtanje pnlZaCrtanje = new PanelZaCrtanje();
		pnlZaCrtanje.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) 
			{
				if (tglbtnCrtajKrug.isSelected())
				{
					pnlZaCrtanje.crtamoLiniju = false;
				} else
				{
					pnlZaCrtanje.crtamoLiniju = true;
				}
				pnlZaCrtanje.iscrtavanje(arg0.getX(), arg0.getY());
			}
		});
		pnlZaCrtanje.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if (tglbtnCrtajDuz.isSelected())
				{
					lblStatus.setText(pnlZaCrtanje.crtajDuz(arg0.getX(), arg0.getY()));
				} else 
				{
					lblStatus.setText(pnlZaCrtanje.crtajKrug(arg0.getX(), arg0.getY()));
				}
			}
		});
		pnlZaCrtanje.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmKaoPaint.getContentPane().add(pnlZaCrtanje, BorderLayout.CENTER);
		
		JButton btnTest = new JButton("Proba");
		btnTest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Duz nekaLinija = new Duz(new Point(10,10), new Point(150,150));
				pnlZaCrtanje.duzi.add(nekaLinija);
				nekaLinija = new Duz(new Point(50, 100), new Point(42, 38));
				pnlZaCrtanje.duzi.add(nekaLinija);
				pnlZaCrtanje.repaint();
			}
		});
		pnlZaAlate.setLayout(new BoxLayout(pnlZaAlate, BoxLayout.Y_AXIS));
		pnlZaAlate.add(btnTest);
		

		
		ButtonGroup grupaZaCrtanje = new ButtonGroup();
		grupaZaCrtanje.add(tglbtnCrtajDuz);
		grupaZaCrtanje.add(tglbtnCrtajKrug);
		
	}

}
