package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.GameMember;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.RowHoverListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class MemberTable extends JTable implements RowHoverListener{

	private static final String[] HEADERS = {"Gender", "Name", "Nickname"};
	private static final float[] HEADER_WIDTHS = {0.1F, 0.45F, 0.45F};
	
	private HashMap<Integer, GameMember> memberIndex = new HashMap<>();
	
	private PanelManageTributes tributesPanel;
	
	private boolean participation;
	
	private MemberTableRenderer renderer;
	
	public MemberTable(PanelManageTributes tributesPanel, boolean participation){
		super(new String[0][HEADERS.length], HEADERS);
		
		this.tributesPanel = tributesPanel;
		this.participation = participation;
		
		DefaultTableModel model = new DefaultTableModel(new String[0][HEADERS.length], HEADERS);
		this.setModel(model);
		
		this.setAutoCreateRowSorter(true);
		
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		
		this.renderer = new MemberTableRenderer();
		this.setDefaultRenderer(String.class, renderer);
		((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new GenderComboBox(this)));
		getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new NameTextField(this)));
		getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new NicknameTextField(this)));
		
		for(int i = 0;i < HEADERS.length;i++){
			getColumnModel().getColumn(i).setCellRenderer(this.renderer);
		}
		
		this.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
			//Get selected row indices
			int[] indices = this.getSelectedRows();
			
			//Convert to model indices so we have the correct values after sorting
			for(int i = 0;i < indices.length;i++){
				indices[i] = this.convertRowIndexToModel(indices[i]);
			}
			
			//Map each selected index to its corresponding Member
			tributesPanel.setSelectedMembers(Arrays.stream(indices).mapToObj(i -> this.memberIndex.get(i)).toArray(GameMember[]::new));
			
			//Update button panel to reflect currently selected members
			tributesPanel.updateButtonPanel();
		});
		
		this.registerHoverAdapters();
	}

	public PanelManageTributes getTributesPanel(){
		return this.tributesPanel;
	}
	
	public GameMember getMemberAt(int row){
		return this.memberIndex.get(row);
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		return true;
	}
	
	public void update(){
		double tableWidth = getPreferredSize().getWidth();

		//Apply table header widths
		for(int i = 0;i < HEADERS.length;i++){
			this.getColumnModel().getColumn(i).setPreferredWidth((int) (tableWidth * HEADER_WIDTHS[i]));
		}

		//Clear the table
		((DefaultTableModel) this.getModel()).setRowCount(0);
		
		//Settings that affect the table
		//TODO: Use some sort of setting filter interface to make this cleaner
		boolean showBots = this.tributesPanel.getSettings().showBots.getValue();
		boolean showCustom = this.tributesPanel.getSettings().showCustom.getValue();
		
		//Generate new rows based on a participation value
		int index = 0;
		for(GameMember m : this.tributesPanel.getMembers().getMembersByParticipation(this.participation)){
			if(m.isBot() && !showBots) continue;
			if(m.isCustom() && !showCustom) continue;

			String[] row = {m.getGender().toString(), m.getName(), m.getNickname()};
			((DefaultTableModel) this.getModel()).addRow(row);
			memberIndex.put(index, m);
			index++;
		}
	}

	@Override
	public Rectangle getBoundsForRow(int row){
		int width = 0;
		for(int i = 0;i < this.getColumnCount();i++){
			width += this.getColumnModel().getColumn(i).getWidth();
		}
		
		Rectangle rect = this.getCellRect(row, 0, true);
		rect.width = width;

		return rect;
	}

	@Override
	public int getNumRows(){
		return this.getModel().getRowCount();
	}

	@Override
	public int getIndex(){
		int index = this.renderer.getHoveredIndex();
		if(index == -1) return index;
		
		return this.convertRowIndexToView(index);
	}
	
	@Override
	public void setIndex(int index){
		this.renderer.setHoveredIndex(this.convertRowIndexToModel(index));
		this.repaint();
	}

	@Override
	public void reset(){
		this.renderer.setHoveredIndex(-1);
		this.repaint();
	}
}
