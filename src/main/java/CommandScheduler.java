package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandScheduler {

	private static final List<ScheduledCommand> SCHEDULED_COMMANDS = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		loadCommands("tmp/commands.txt");

		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.scheduleAtFixedRate(() -> {
			LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
			for (ScheduledCommand cmd : SCHEDULED_COMMANDS) {
				if (cmd.isRecurring()) {
					if (now.getMinute() % cmd.getInterval() == 0) {
						executeCommand(cmd);
					}
				} else {
					if (!cmd.isExecuted() && cmd.getScheduledTime().equals(now)) {
						executeCommand(cmd);
						cmd.setExecuted(true);
					}
				}
			}
		}, 0, 1, TimeUnit.MINUTES);
	}

	private static void loadCommands(String filePath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.startsWith("*/")) {
				String[] parts = line.split(" ", 2);
				int interval = Integer.parseInt(parts[0].substring(2));
				String command = parts[1];
				SCHEDULED_COMMANDS.add(new ScheduledCommand(interval, command));
			} else {
				String[] parts = line.split(" ", 6);
				if (parts.length < 6) {
					continue;
				}

				int minute = Integer.parseInt(parts[0]);
				int hour = Integer.parseInt(parts[1]);
				int day = Integer.parseInt(parts[2]);
				int month = Integer.parseInt(parts[3]);
				int year = Integer.parseInt(parts[4]);
				String command = parts[5];

				LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
				SCHEDULED_COMMANDS.add(new ScheduledCommand(dateTime, command));
			}
		}
		reader.close();
	}

	private static void executeCommand(ScheduledCommand cmd) {
		System.out.println("Executing: " + cmd.getCommand());
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd.getCommand());
			pb.inheritIO();
			Process process = pb.start();
			process.waitFor();
		} catch (Exception e) {
			System.err.println("Error executing command: " + e.getMessage());
		}
		System.out.println();
	}
}