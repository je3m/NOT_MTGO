package back_end;
/**
 * Represents the various phases in MTG
 *
 */
public enum Phase {
	UNTAP1 {
        @Override
        public String toString() {
            return "Untap";
        }
    },
	UPKEEP1 {
        @Override
        public String toString() {
            return "Upkeep";
        }
    },
	DRAW1 {
        @Override
        public String toString() {
            return "Draw";
        }
    },
	FIRST_MAIN1 {
        @Override
        public String toString() {
            return "Main 1";
        }
    },
	START_COMBAT1 {
        @Override
        public String toString() {
            return "Begin Combat";
        }
    },
	DECLARE_ATTACKERS1 {
        @Override
        public String toString() {
            return "Declare Attacks";
        }
    },
	DECLARE_BLOCKERS1 {
        @Override
        public String toString() {
            return "Declare Blocks";
        }
    },
	COMBAT_DAMAGE1 {
        @Override
        public String toString() {
            return "Combat Damage";
        }
    },
	END_OF_COMBAT1 {
        @Override
        public String toString() {
            return "End Combat";
        }
    },
	SECOND_MAIN1 {
        @Override
        public String toString() {
            return "Main 2";
        }
    },
	END1 {
        @Override
        public String toString() {
            return "End Turn";
        }
    },
	CLEANUP1 {
        @Override
        public String toString() {
            return "Cleanup";
        }
    },
	UNTAP2 {
        @Override
        public String toString() {
            return "Untap";
        }
    },
	UPKEEP2 {
        @Override
        public String toString() {
            return "Upkeep";
        }
    },
	DRAW2 {
        @Override
        public String toString() {
            return "Draw";
        }
    },
	FIRST_MAIN2 {
        @Override
        public String toString() {
            return "Main 1";
        }
    },
	START_COMBAT2 {
        @Override
        public String toString() {
            return "Begin Combat";
        }
    },
	DECLARE_ATTACKERS2 {
        @Override
        public String toString() {
            return "Declare Attacks";
        }
    },
	DECLARE_BLOCKERS2 {
        @Override
        public String toString() {
            return "Declare Blocks";
        }
    },
	COMBAT_DAMAGE2 {
        @Override
        public String toString() {
            return "Combat Damage";
        }
    },
	END_OF_COMBAT2 {
        @Override
        public String toString() {
            return "End Combat";
        }
    },
	SECOND_MAIN2 {
        @Override
        public String toString() {
            return "Main 2";
        }
    },
	END2 {
        @Override
        public String toString() {
            return "End Turn";
        }
    },
	CLEANUP2 {
        @Override
        public String toString() {
            return "Cleanup";
        }
    };
}
