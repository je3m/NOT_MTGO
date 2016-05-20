package back_end;
/**
 * Represents the various phases in MTG
 *
 */
public enum Phase {
	UNTAP1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.0"); //$NON-NLS-1$
        }
    },
	UPKEEP1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.1"); //$NON-NLS-1$
        }
    },
	DRAW1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.2"); //$NON-NLS-1$
        }
    },
	FIRST_MAIN1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.3"); //$NON-NLS-1$
        }
    },
	START_COMBAT1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.4"); //$NON-NLS-1$
        }
    },
	DECLARE_ATTACKERS1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.5"); //$NON-NLS-1$
        }
    },
	DECLARE_BLOCKERS1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.6"); //$NON-NLS-1$
        }
    },
	COMBAT_DAMAGE1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.7"); //$NON-NLS-1$
        }
    },
	END_OF_COMBAT1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.8"); //$NON-NLS-1$
        }
    },
	SECOND_MAIN1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.9"); //$NON-NLS-1$
        }
    },
	END1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.10"); //$NON-NLS-1$
        }
    },
	CLEANUP1 {
        @Override
        public String toString() {
            return Messages.getString("Phase.11"); //$NON-NLS-1$
        }
    },
	UNTAP2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.12"); //$NON-NLS-1$
        }
    },
	UPKEEP2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.13"); //$NON-NLS-1$
        }
    },
	DRAW2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.14"); //$NON-NLS-1$
        }
    },
	FIRST_MAIN2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.15"); //$NON-NLS-1$
        }
    },
	START_COMBAT2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.16"); //$NON-NLS-1$
        }
    },
	DECLARE_ATTACKERS2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.17"); //$NON-NLS-1$
        }
    },
	DECLARE_BLOCKERS2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.18"); //$NON-NLS-1$
        }
    },
	COMBAT_DAMAGE2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.19"); //$NON-NLS-1$
        }
    },
	END_OF_COMBAT2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.20"); //$NON-NLS-1$
        }
    },
	SECOND_MAIN2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.21"); //$NON-NLS-1$
        }
    },
	END2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.22"); //$NON-NLS-1$
        }
    },
	CLEANUP2 {
        @Override
        public String toString() {
            return Messages.getString("Phase.23"); //$NON-NLS-1$
        }
    };
}
