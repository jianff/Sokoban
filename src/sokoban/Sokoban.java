package sokoban;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Sokoban extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 16;
	public static final int HIGHT = 12;
	public static final int cell = 50;
	
	public  JLabel lab_wolf=new JLabel();
	public  JLabel lab_tree=new JLabel();
	public  JLabel lab_cage=new JLabel();
	public  JLabel lab_sheep=new JLabel();
	
	LinkedList<Point> map_tree=new  LinkedList<Point>();
	LinkedList<Point> map_cage=new  LinkedList<Point>();
	LinkedList<Point> map_sheep=new LinkedList<Point>();
	LinkedList<Point> map_wolf=new LinkedList<Point>();
	
	Map<String,JLabel> hm_tree= new HashMap<>();
	Map<String,JLabel> hm_cage= new HashMap<>();
	Map<String,JLabel> hm_sheep=new HashMap<>();
	
	int currentsheep;//��ǰ��ץ������
	
	
	 
	//�ڴ������ͼ����
	//��ͼ��1�����ǣ�2������3����������4������ľ
		 								   		  
    private static int background[][]= new int[][]{{4,4,4,4,4,4,4,4,4,4,4},
    											   {4,4,0,0,0,0,0,0,0,4,4},
    											   {4,0,0,0,2,1,2,0,0,4,4},
    											   {4,0,0,2,0,2,0,2,0,4,4},
    											   {4,4,4,4,4,2,4,4,4,4,4},
    											   {0,0,0,4,4,0,3,0,3,3,4},
    											   {0,0,0,4,0,0,3,4,0,0,4},
    											   {0,0,0,4,0,3,0,0,0,3,4},
    											   {0,0,0,4,4,4,4,0,0,4,4},
    											   {0,0,0,0,0,0,4,4,4,4,4}};
	
	 

	//��ʼ������
	public Sokoban(){
		this.setTitle("������֮�����ԭ");
		this.setSize(WIDTH*cell+30,HIGHT*cell+50);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//��Ӱ���������
		this.addKeyListener(this);
	}
	
	
	
	//���������ͼ����
	public void background(){

		for(int i=0;i<background.length;i++){
			for(int j=0;j<background[i].length;j++){

				if(background[i][j]==1){
					map_wolf.add(new Point(j*cell,i*cell));
				}
				
				if(background[i][j]==2){
					map_sheep.add(new Point(j*cell,i*cell));
				}
				
				if(background[i][j]==3){
					map_cage.add(new Point(j*cell,i*cell));
				}
				
				if(background[i][j]==4){
					map_tree.add(new Point(j*cell,i*cell));	
				}
			}
			
		}
		
	}
	
	

	
	//��ʼ����ͼ
	public void backgroundInit(){
		
		//��ʼ����
		Point wolf=map_wolf.get(0);
		Icon icon_wolf=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\wolf_down.png");
		lab_wolf.setIcon(icon_wolf);
		lab_wolf.setBounds(wolf.x,wolf.y, cell, cell);
		this.add(lab_wolf);

		//��ʼ����ľ
		for(int i=0;i<map_tree.size();i++){
			JLabel lab_tree=new JLabel();
			hm_tree.put("key"+i,lab_tree);	
			
			Point tree=map_tree.get(i);
			Icon icon_tree=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\tree.png");
			lab_tree=hm_tree.get("key"+i);
			lab_tree.setIcon(icon_tree);
			lab_tree.setBounds(tree.x, tree.y, cell, cell);
			this.add(lab_tree);	
			}
		
		//��ʼ������
		for(int i=0;i<map_cage.size();i++){
			JLabel lab_cage=new JLabel();
			hm_cage.put("key"+i,lab_cage);	
			
			Point cage=map_cage.get(i);
			Icon icon_cage=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\cage.png");
			lab_cage=hm_cage.get("key"+i);
			lab_cage.setIcon(icon_cage);
			lab_cage.setBounds(cage.x, cage.y, cell, cell);
			this.add(lab_cage);	
		}
		
		//��ʼ����
		for(int i=0;i<map_sheep.size();i++){
			JLabel lab_sheep=new JLabel();
			hm_sheep.put("key"+i,lab_sheep);	
					
			Point sheep=map_sheep.get(i);
			Icon icon_sheep=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\sheep.png");
			lab_sheep=hm_sheep.get("key"+i);
			lab_sheep.setIcon(icon_sheep);
			lab_sheep.setBounds(sheep.x, sheep.y, cell, cell);
			this.add(lab_sheep);	
			}
		
		//��ʼ������
		Icon icon_bg=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\floor.png");
		JLabel lab_bg=new JLabel(icon_bg);
		lab_bg.setBounds(0, 0, WIDTH*cell, HIGHT*cell);
		this.add(lab_bg);
	}
	
	
	
	//�ж��Ƿ�Ӯ����Ϸ
	public boolean isGameover(){
		int sum=0;
		for(int i=0;i<map_sheep.size();i++){
			for(int j=0;j<map_cage.size();j++){
				if(hm_sheep.get("key"+i).getLocation().equals(map_cage.get(j))){
					sum=sum+1;
					Icon icon=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\cry.png");
					hm_sheep.get("key"+i).setIcon(icon);
				} 	
			}	
		}
		if(sum==map_sheep.size()){
			return true;
		}
		return false;
	}
	
	
	
	
	//������ƶ�����Ϸ���򣨺��ģ�
	@Override
	public void keyPressed(KeyEvent e) {
		
		int x=lab_wolf.getLocation().x;
		int y=lab_wolf.getLocation().y;
		
		int code=e.getKeyCode();
		switch(code){
		
		case 37:
			boolean wolf_arrive_left =false;
			boolean sheep_arrive_left=false;
			boolean wolf_sheep_left  =false;//�����Ƿ�����
			
			Icon icon_wolf_left=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\wolf_left.png");
			lab_wolf.setIcon(icon_wolf_left);
			
			//�ж������Ƿ�����
			for(int i=0;i<hm_sheep.size();i++){
				lab_sheep=hm_sheep.get("key"+i);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				if(x-cell==A1&&y==B1){
					wolf_sheep_left=true;
					currentsheep=i;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			for(int i=0;i<map_tree.size();i++){
				lab_tree=hm_tree.get("key"+i);
				int U1=lab_tree.getLocation().x;
				int V1=lab_tree.getLocation().y;
				if(x-50==U1&&y==V1||x==0){
					wolf_arrive_left=true;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			if(wolf_sheep_left){
				for(int i=0;i<map_tree.size();i++){
					lab_tree=hm_tree.get("key"+i);
					int U1=lab_tree.getLocation().x;
					int V1=lab_tree.getLocation().y;
					
					int A1=hm_sheep.get("key"+currentsheep).getLocation().x;
					int B1=hm_sheep.get("key"+currentsheep).getLocation().y;
					
					int A11=A1;
					int B11=B1;
					if(hm_sheep.get("key"+i)!=null){
					lab_sheep=hm_sheep.get("key"+i);
					A11=lab_sheep.getLocation().x;
					B11=lab_sheep.getLocation().y;
					}
					if(A1-50==U1&&B1==V1||A1==0||A1-50==A11&&B1==B11){
						sheep_arrive_left=true;
						break;
					}
				}
			}
			
			//�����������ϰ�
			if(sheep_arrive_left&&wolf_sheep_left){
				break;
			}
			
			//�������ϰ�
			if(wolf_arrive_left){
				break;
			}
		
			lab_wolf.setLocation(x-cell, y);//�ǵ��ƶ�
			
			//�������ƶ�
			if(wolf_sheep_left){
				lab_sheep=hm_sheep.get("key"+currentsheep);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				lab_sheep.setLocation(A1-cell, B1);//����ƶ�	
				Icon icon=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\sheep.png");
				lab_sheep.setIcon(icon);
			}

			//�ж��Ƿ������Ϸ
			if(isGameover()){
				JOptionPane.showMessageDialog(this, "YOU WIN!", 
						"��ʾ",JOptionPane.WARNING_MESSAGE);
			}
			
			break;
		
		case 38:
			boolean wolf_arrive_up =false;
			boolean sheep_arrive_up=false;
			boolean wolf_sheep_up  =false;//�����Ƿ�����
			
			Icon icon_wolf_up=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\wolf_up.png");
			lab_wolf.setIcon(icon_wolf_up);
			
			//�ж������Ƿ�����
			for(int i=0;i<hm_sheep.size();i++){
				lab_sheep=hm_sheep.get("key"+i);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				if(x==A1&&y-50==B1){
					wolf_sheep_up=true;
					currentsheep=i;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			for(int i=0;i<map_tree.size();i++){
				lab_tree=hm_tree.get("key"+i);
				int U1=lab_tree.getLocation().x;
				int V1=lab_tree.getLocation().y;
				if(x==U1&&y-50==V1||y==0){
					wolf_arrive_up=true;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			if(wolf_sheep_up){
				for(int i=0;i<map_tree.size();i++){
					lab_tree=hm_tree.get("key"+i);
					int U1=lab_tree.getLocation().x;
					int V1=lab_tree.getLocation().y;
					
					int A1=hm_sheep.get("key"+currentsheep).getLocation().x;
					int B1=hm_sheep.get("key"+currentsheep).getLocation().y;
					
					int A11=A1;
					int B11=B1;
					if(hm_sheep.get("key"+i)!=null){
					lab_sheep=hm_sheep.get("key"+i);
					A11=lab_sheep.getLocation().x;
					B11=lab_sheep.getLocation().y;
					}
					if(A1==U1&&B1-50==V1||B1==0||A1==A11&&B1-50==B11){
						sheep_arrive_up=true;
						break;
					}
				}
			}
			
			//�����������ϰ�
			if(sheep_arrive_up&&wolf_sheep_up){
				break;
			}
			
			//�������ϰ�
			if(wolf_arrive_up){
				break;
			}
		
			lab_wolf.setLocation(x, y-cell);//�ǵ��ƶ�
			
			//�������ƶ�
			if(wolf_sheep_up){
				lab_sheep=hm_sheep.get("key"+currentsheep);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				lab_sheep.setLocation(A1, B1-cell);//����ƶ�	
				Icon icon=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\sheep.png");
				lab_sheep.setIcon(icon);
				}
			
			//�ж��Ƿ������Ϸ
			if(isGameover()){
				JOptionPane.showMessageDialog(this, "YOU WIN!", 
						"��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			
			break;
			
		case 39:
			boolean wolf_arrive_right =false;
			boolean sheep_arrive_right=false;
			boolean wolf_sheep_right  =false;//�����Ƿ�����
			
			Icon icon_wolf_right=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\wolf_right.png");
			lab_wolf.setIcon(icon_wolf_right);
			
			//�ж������Ƿ�����
			for(int i=0;i<hm_sheep.size();i++){
				lab_sheep=hm_sheep.get("key"+i);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				if(x+cell==A1&&y==B1){
					wolf_sheep_right=true;
					currentsheep=i;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			for(int i=0;i<map_tree.size();i++){
				lab_tree=hm_tree.get("key"+i);
				int U1=lab_tree.getLocation().x;
				int V1=lab_tree.getLocation().y;
				if(x+50==U1&&y==V1||x==15*cell){
					wolf_arrive_right=true;
					break;
				}
			}
			
			//�ж��ƶ���ʱ�Ƿ������ϰ�
			if(wolf_sheep_right){
				for(int i=0;i<map_tree.size();i++){
					lab_tree=hm_tree.get("key"+i);
					int U1=lab_tree.getLocation().x;
					int V1=lab_tree.getLocation().y;
					
					int A1=hm_sheep.get("key"+currentsheep).getLocation().x;
					int B1=hm_sheep.get("key"+currentsheep).getLocation().y;
					
					int A11=A1;
					int B11=B1;
					if(hm_sheep.get("key"+i)!=null){
					lab_sheep=hm_sheep.get("key"+i);
					A11=lab_sheep.getLocation().x;
					B11=lab_sheep.getLocation().y;
					}
					if(A1+50==U1&&B1==V1||A1==15*cell||A1+50==A11&&B1==B11){
						sheep_arrive_right=true;
						break;
					}
				}
			}
			
			//�����������ϰ�
			if(sheep_arrive_right&&wolf_sheep_right){
				break;
			}
			
			//�������ϰ�
			if(wolf_arrive_right){
				break;
			}
		
			lab_wolf.setLocation(x+cell, y);//�ǵ��ƶ�
			
			//�������ƶ�
			if(wolf_sheep_right){
				lab_sheep=hm_sheep.get("key"+currentsheep);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				lab_sheep.setLocation(A1+cell, B1);//����ƶ�	
				Icon icon=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\sheep.png");
				lab_sheep.setIcon(icon);
				}
			
			//�ж��Ƿ������Ϸ
			if(isGameover()){
				JOptionPane.showMessageDialog(this, "YOU WIN!", 
						"��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			
			break;
			
		case 40:
			boolean wolf_arrive_down =false;
			boolean sheep_arrive_down=false;
			boolean wolf_sheep_down  =false;//�����Ƿ�����
			
			Icon icon_wolf_down=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\wolf_down.png");
			lab_wolf.setIcon(icon_wolf_down);
			
			//�ж������Ƿ�����
			for(int i=0;i<hm_sheep.size();i++){
				lab_sheep=hm_sheep.get("key"+i);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				if(x==A1&&y+cell==B1){
					wolf_sheep_down=true;
					currentsheep=i;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			for(int i=0;i<map_tree.size();i++){
				lab_tree=hm_tree.get("key"+i);
				int U1=lab_tree.getLocation().x;
				int V1=lab_tree.getLocation().y;
				if(x==U1&&y+50==V1||y==11*cell){
					wolf_arrive_down=true;
					break;
				}
			}
			
			//�ж����Ƿ������ϰ�
			if(wolf_sheep_down){
				for(int i=0;i<map_tree.size();i++){
					lab_tree=hm_tree.get("key"+i);
					int U1=lab_tree.getLocation().x;
					int V1=lab_tree.getLocation().y;
					
					int A1=hm_sheep.get("key"+currentsheep).getLocation().x;
					int B1=hm_sheep.get("key"+currentsheep).getLocation().y;
					
					int A11=A1;
					int B11=B1;
					if(hm_sheep.get("key"+i)!=null){
					lab_sheep=hm_sheep.get("key"+i);
					A11=lab_sheep.getLocation().x;
					B11=lab_sheep.getLocation().y;
					}
					if(A1==U1&&B1+50==V1||B1==11*cell||A1==A11&&B1+50==B11){
						sheep_arrive_down=true;
						break;
					}
				}
			}
			
			//�����������ϰ�
			if(sheep_arrive_down&&wolf_sheep_down){
				break;
			}
			
			//�������ϰ�
			if(wolf_arrive_down){
				break;
			}
		
			lab_wolf.setLocation(x, y+cell);//�ǵ��ƶ�
			
			//�������ƶ�
			if(wolf_sheep_down){
				lab_sheep=hm_sheep.get("key"+currentsheep);
				int A1=lab_sheep.getLocation().x;
				int B1=lab_sheep.getLocation().y;
				lab_sheep.setLocation(A1, B1+cell);//����ƶ�	
				Icon icon=new ImageIcon("D:\\Java�ļ�\\workspace.old\\Box\\sheep.png");
				lab_sheep.setIcon(icon);
				}
			
			//�ж��Ƿ������Ϸ
			if(isGameover()){
				JOptionPane.showMessageDialog(this, "YOU WIN!", 
						"��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			
			break;	
		}	
	}


	
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}
	

	
	
	//������
	public static void main(String[] args){

		Sokoban Sokoban=new Sokoban();
		Sokoban.background();
		Sokoban.backgroundInit();
		Sokoban.setVisible(true);
	
	}
}
