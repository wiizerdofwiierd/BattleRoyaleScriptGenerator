package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.theme.UIConstants;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MemberTableRenderer extends DefaultTableCellRenderer{
	
	private static final int IMAGE_WIDTH = 64;
	private static final int IMAGE_HEIGHT = 64;
	
	private int hoveredIndex = -1;
	
	public MemberTableRenderer(){
		super();
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
			label.setBackground(UIConstants.Colors.ELEMENT_SELECTED);
		}
		else if(this.hoveredIndex == row){
			label.setBackground(UIConstants.Colors.ELEMENT_HOVERED);
		}
		else if(col != 0 && !Member.validateName(label.getText())){
			label.setBackground(UIConstants.Colors.TABLE_NAME_INVALID);
			label.setToolTipText("Name is not valid");
		}
		else if(currentMember.isBot()){
			label.setBackground(UIConstants.Colors.TABLE_MEMBER_BOT);
		}
		else if(currentMember.isCustom()){
			label.setBackground(UIConstants.Colors.TABLE_MEMBER_CUSTOM);
			label.setToolTipText(Util.formatHTMLImageTag(currentMember.getCustomImgURL(), IMAGE_WIDTH, IMAGE_HEIGHT));
		}
		else{
			label.setBackground(Color.WHITE);
		}
		
		return label;
	}

	public int getHoveredIndex(){
		return this.hoveredIndex;
	}

	public void setHoveredIndex(int index){
		this.hoveredIndex = index;
	}
}
