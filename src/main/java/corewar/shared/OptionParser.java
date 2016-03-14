package corewar.shared;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public final class OptionParser {

	private OptionParser() {
	}

	public static Optional<Options> parse(String[] args) {
		int verbosity = 0;
		long firstCycle = 0;
		long lastCycle = Long.MAX_VALUE;
		String file = "";
		try {
			checkArgument(args.length > 0);
			for (int i = 0; i < args.length; i++) {
				String arg = args[i];
				if ("-v".equals(arg)) {
					verbosity = Integer.parseInt(args[i + 1]);
					i++;
				} else if ("-f".equals(arg)) {
					firstCycle = Integer.parseInt(args[i + 1]);
					i++;
				} else if ("-l".equals(arg)) {
					lastCycle = Integer.parseInt(args[i + 1]);
					i++;
				} else {
					file = arg;
				}
			}
		} catch (Throwable t) {
			return Optional.empty();
		}
		return Optional.of(new Options(verbosity, firstCycle, lastCycle, file));
	}

	public static final class Options {

		private final int verbosity;
		private final long firstCycle;
		private final long lastCycle;
		private final String file;

		private Options(int verbosity, long firstCycle, long lastCycle, String file) {
			this.verbosity = verbosity;
			this.firstCycle = firstCycle;
			this.lastCycle = lastCycle;
			this.file = file;
		}

		public int getVerbosity() {
			return verbosity;
		}

		public String getFile() {
			return file;
		}

		public long getFirstCycle() {
			return firstCycle;
		}

		public long getLastCycle() {
			return lastCycle;
		}
	}
}
