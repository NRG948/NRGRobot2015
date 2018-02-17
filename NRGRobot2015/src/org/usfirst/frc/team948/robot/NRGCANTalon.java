package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class NRGCANTalon extends CANTalon implements LiveWindowSendable {
	private ITable m_table;
	private ITableListener m_table_listener;
	
	

	public NRGCANTalon(int deviceNumber) {
		super(deviceNumber);
	}

	public NRGCANTalon(int deviceNumber, int controlPeriodMs) {
		super(deviceNumber, controlPeriodMs);
	}

	@Override
	public void initTable(ITable subtable) {
		m_table = subtable;

	}

	@Override
	public ITable getTable() {
		// TODO Auto-generated method stub
		return m_table;
	}

	@Override
	public String getSmartDashboardType() {
		// TODO Auto-generated method stub
		return "Speed Controller";
	}

	@Override
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Value", getSpeed());
		}

	}

	@Override
	public void startLiveWindowMode() {
		set(0);
		m_table_listener = new ITableListener() {
			public void valueChanged(ITable itable, String key, Object value,
					boolean bln) {
				set(((Double) value).doubleValue());
			}
		};
		m_table.addTableListener("Value", m_table_listener, true);

	}

	@Override
	public void stopLiveWindowMode() {
		set(0);
		// TODO Auto-generated method stub
		// TODO: Broken, should only remove the listener from "Value" only.
		m_table.removeTableListener(m_table_listener);
	}
	
}
