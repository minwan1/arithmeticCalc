import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class arithmetic
{
	private static boolean _init = false;
	private static ScriptEngineFactory factory;
	private static ScriptEngine engine;
	
	private static void init(){
		ScriptEngineManager sem = new ScriptEngineManager();
		engine = sem.getEngineByName("ECMAScript");
		factory = engine.getFactory();
		arithmetic._init = true;
	}
	
	static String eval(String exp){
		if(arithmetic._init == false){
			arithmetic.init();
		}
		Object result = null;
		String exps[] = {exp};
		String statement = "function calculate(exp) {return eval(exp); }";
		try{
			engine.eval(statement);
			Invocable iv = (Invocable)engine;
			result = iv.invokeFunction("calculate", exps);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result.toString();
	}
	
}

class calc extends JFrame implements ActionListener
{
	JLabel text;
	JButton[] calButton;
	String[] num={"c","7","8","9","/",
			"mc","4","5","6","*",
			"mr","1","2","3","-",
			"m+","0",".","=","+"			
	};
	String first="";
	
	
	JPanel textJPanel,calJPanel;
	
	JMenuItem menuItem=new JMenuItem("정보");
	JMenuItem exitItem=new JMenuItem("종료");
	JMenuItem f1Item=new JMenuItem("도움말 보기(V)       F1");
	JMenuItem f2item=new JMenuItem("계산기 정보(A)");
	
	
	JMenu menu=new JMenu("보기(V)");
	JMenu menu1=new JMenu("편집(E)");
	JMenu domeJMenu=new JMenu("도움말(H)");
	
	

	JMenuBar asdf=new JMenuBar();

	
	public calc()
	{
		super("calc");
		setBounds(0, 0, 300, 300);
		setLayout(new BorderLayout());
		
	
		menu.add(menuItem);		
		menu.add(exitItem);
		domeJMenu.add(f1Item);
		domeJMenu.add(f2item);
		asdf.add(menu);
		asdf.add(menu1);
		asdf.add(domeJMenu);
		
		
		setJMenuBar(asdf);
		
		
		

		calJPanel =new JPanel();
		textJPanel =new JPanel();	
		textJPanel.setLayout(new GridLayout());
		textJPanel.setBackground(Color.WHITE);		
		text=new JLabel("0",JLabel.RIGHT);
		textJPanel.add(text);
		
		calJPanel.setLayout(new GridLayout(4, 4, 2, 2));


		
		calButton=new JButton[num.length];
		for(int i=0;i<num.length;i++)
		{
			calButton[i]=new JButton(num[i]);
			calJPanel.add(calButton[i]);
			calButton[i].addActionListener(this);
			
		}
		add(calJPanel,BorderLayout.CENTER);
		add(textJPanel,BorderLayout.NORTH);
		
		f2item.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				JOptionPane.showMessageDialog(calJPanel, "Hello, World!", "dialog 2", getDefaultCloseOperation());
			}
		});

		
		setVisible(true);
		
		exitItem.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
				
			}
		});
	
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String str=e.getActionCommand();
	
		
		if(str!="c"&&str!="="&&str!="MC"&&str!="MR"&&str!="M+")
		{
			
			first+=str;
			text.setText(first);
			
		}else if(str=="c")
		{
			first="";
			text.setText("0");
		}else if(str=="="){
			arithmetic arith=new arithmetic();
			text.setText(arith.eval(first));
			first=arith.eval(first);
			
		}

		

	}
	
}
public class calculator
{

	public static void main(String[] args)
	{
		calc cal =new calc();

	}

}