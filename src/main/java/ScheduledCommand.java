package main.java;

import java.time.LocalDateTime;

public class ScheduledCommand {
	private LocalDateTime scheduledTime;
	private int interval;
	private String command;
	private boolean isRecurring;
	private boolean isExecuted;

	public ScheduledCommand(LocalDateTime scheduledTime, String command) {
		this.scheduledTime = scheduledTime;
		this.command = command;
		this.isRecurring = false;
		this.isExecuted = false;
	}

	public ScheduledCommand(int interval, String command) {
		this.interval = interval;
		this.command = command;
		this.isRecurring = true;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public boolean isRecurring() {
		return isRecurring;
	}

	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
	}

	public boolean isExecuted() {
		return isExecuted;
	}

	public void setExecuted(boolean isExecuted) {
		this.isExecuted = isExecuted;
	}
}