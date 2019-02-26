package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractListPanel extends JPanel{

	private static ArrayList<AbstractListPanel> panels = new ArrayList<>();
	
	protected WindowGameManage mainWindow;
	
	private MemberTable memberTable;

	public AbstractListPanel(WindowGameManage mainWindow, String borderTitle, boolean participation){
		this.mainWindow = mainWindow;
		
		TitledBorder border = BorderFactory.createTitledBorder(borderTitle);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);

		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		this.memberTable = new MemberTable(this.mainWindow, participation);
		this.memberTable.getSelectionModel().addListSelectionListener(actionEvent -> {
			for(AbstractListPanel a : this.panels){
				if(a != this){
					a.memberTable.clearSelection();
				}
			}
			
			this.mainWindow.updateButtonPanel();
		});

		JScrollPane scrollPane = new JScrollPane(
				memberTable, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		this.add(scrollPane, c);

		this.memberTable.update();
		
		panels.add(this);
	}
	
	public MemberTable getTable(){
		return this.memberTable;
	}
	
	public void update(){
		this.memberTable.update();
	}
}
