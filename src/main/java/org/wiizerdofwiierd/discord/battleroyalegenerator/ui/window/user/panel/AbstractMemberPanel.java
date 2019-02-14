package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api.AbstractBotPanel;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.table.MemberTable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public abstract class AbstractMemberPanel extends AbstractBotPanel{

	protected MemberTable memberTable;

	public AbstractMemberPanel(WindowUserSelect parentFrame, String borderTitle, boolean participation){
		super(parentFrame);

		TitledBorder border = BorderFactory.createTitledBorder(borderTitle);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);

		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;


		this.memberTable = new MemberTable(this.parentFrame, participation);

		JScrollPane scrollPane = new JScrollPane(
				memberTable, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		this.add(scrollPane, c);

		this.memberTable.update();
	}
	
	public MemberTable getTable(){
		return this.memberTable;
	}
	
	@Override
	public void update(){
		this.memberTable.update();
	}
}
