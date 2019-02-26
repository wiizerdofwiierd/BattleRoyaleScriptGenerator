package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Gender;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.HashMap;

public class MemberTable extends JTable{

	private static final String[] HEADERS = {"Gender", "Name", "Nickname"};
	private static final float[] HEADER_WIDTHS = {0.1F, 0.45F, 0.45F};
	
	private HashMap<Integer, Member> memberIndex = new HashMap<>();
	
	private WindowGameManage parentFrame;
	
	private boolean participation;
	
	public MemberTable(WindowGameManage parentFrame, boolean participation){
		super(new String[0][HEADERS.length], HEADERS);
		
		this.parentFrame = parentFrame;
		this.participation = participation;
		
		DefaultTableModel model = new DefaultTableModel(new String[0][HEADERS.length], HEADERS);
		this.setModel(model);
		
		//TODO: Implement way of associating Members with rows so that sorting can work correctly
		this.setAutoCreateRowSorter(true);
		
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.setDefaultRenderer(String.class, new MemberTableRenderer(this.parentFrame.getSettings()));
		((DefaultTableCellRenderer) this.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new GenderComboBox(this)));
		getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new NameTextField(this)));
		getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new NicknameTextField(this)));
		
		for(int i = 0;i < HEADERS.length;i++){
			getColumnModel().getColumn(i).setCellRenderer(new MemberTableRenderer(this.parentFrame.getSettings()));
		}
		
		this.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
			//Get selected row indices
			int[] indices = this.getSelectedRows();
			
			//Convert to model indices so we have the correct values after sorting
			for(int i = 0;i < indices.length;i++){
				indices[i] = this.convertRowIndexToModel(indices[i]);
			}
			
			//Map each selected index to its corresponding Member
			parentFrame.setSelectedMembers(Arrays.stream(indices).mapToObj(i -> this.memberIndex.get(i)).toArray(Member[]::new));
			
			//Update button panel to reflect currently selected members
			parentFrame.updateButtonPanel();
		});
		
		this.getModel().addTableModelListener(e -> {
			if(e.getType() != TableModelEvent.UPDATE) return;
			
			int row = e.getFirstRow();
			int col = e.getColumn();
			
			if(col != 0) return;
			
			String value = (String) ((TableModel) e.getSource()).getValueAt(row, col);
			Gender selectedGender = Gender.get(value);
			
			getMemberAt(row).setGender(selectedGender);
		});
	}

	public WindowGameManage getParentFrame(){
		return this.parentFrame;
	}
	
	public Member getMemberAt(int row){
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
		boolean showBots = this.parentFrame.getSettings().showBots.getValue();
		boolean showCustom = this.parentFrame.getSettings().showCustom.getValue();
		
		//Generate new rows based on a participation value
		int index = 0;
		for(Member m : this.parentFrame.getMembers().getMembersByParticipation(this.participation)){
			if(m.isBot() && !showBots) continue;
			if(m.isCustom() && !showCustom) continue;

			String[] row = {m.getGender().toString(), m.getName(), m.getNickname()};
			((DefaultTableModel) this.getModel()).addRow(row);
			memberIndex.put(index, m);
			index++;
		}
	}
}
