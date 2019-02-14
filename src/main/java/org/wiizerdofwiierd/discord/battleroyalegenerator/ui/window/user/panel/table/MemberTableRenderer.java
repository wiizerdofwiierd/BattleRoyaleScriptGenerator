package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.table;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MemberTableRenderer extends DefaultTableCellRenderer{
	
	private static final Color COLOR_SELECTED = UIManager.getColor("Table.selectionBackground");
	private static final Color COLOR_INVALID = new Color(255, 130, 130);
	private static final Color COLOR_BOT = new Color(156, 169, 217);
	private static final Color COLOR_CUSTOM = new Color(106, 216, 176);
	
	private static final int IMAGE_WIDTH = 64;
	private static final int IMAGE_HEIGHT = 64;
	
	private Settings settings;
	
	public MemberTableRenderer(Settings settings){
		super();
		this.settings = settings;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
		//Convert from row index to absolute index given by the model; fixes issues when sorting the table
		row = table.convertRowIndexToModel(row);
		
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setToolTipText(null);

		if(!(table instanceof MemberTable))
			return label;

		Member currentMember = ((MemberTable) table).getMemberAt(row);
		
		if(isSelected){
			label.setBackground(COLOR_SELECTED);
		}
		else if(col != 0 && !Member.validateName(label.getText())){
			label.setBackground(COLOR_INVALID);
			label.setToolTipText("Name is not valid");
		}
		else if(currentMember.isBot()){
			label.setBackground(COLOR_BOT);
		}
		else if(currentMember.isCustom()){
			label.setBackground(COLOR_CUSTOM);
			label.setToolTipText(String.format("<html><img src=\"%s\" width=\"%d\" height=\"%d\"></html>", currentMember.getCustomImgURL(), IMAGE_WIDTH, IMAGE_HEIGHT));
		}
		else{
			label.setBackground(Color.WHITE);
		}
		
		if(col == 2){
		}
		
		return label;
	}
}
