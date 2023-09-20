import java.util.Random;

public class Main  {

    public static int bossHeals = 700;
    public static int bossDamage = 50;
    public static int[] heroHeals = {270, 230, 210, 180};
    public static int[] heroDamage = {25, 25, 20, 0};
    public static String[] heroAttackType = {"Warrior", "Magick", "Telepan", "Medic"};

    public static String bossBarrier;

    public static int round = 0;


    public static void main(String[] args) {

        printStatistics();

        while (!isGameFinished()) {
            round++;
            round();
        }
    }

    public static void bossTypeBarrier() {
        Random random = new Random();
        int index = random.nextInt(heroAttackType.length);
        bossBarrier = heroAttackType[index];
        System.out.println(" Boss barier type for " + bossBarrier);
        bossHeals = bossHeals + heroDamage[index];
    }

    public static boolean isGameFinished() {

        boolean allHeroesDead = true;

        if (bossHeals <= 0) {
            System.out.println("HEROES WIN!!!!!");
            return true;
        }
        for (int i = 0; i < heroHeals.length; i++) {
            if (heroHeals[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println("BOSS WIN!!!!!");
        }
        return allHeroesDead;
    }


    public static void printStatistics() {
        System.out.println("STATISTIC");
        System.out.println("Boss heals " + bossHeals + ";" + " damage " + bossDamage);
        for (int i = 0; i < heroAttackType.length; i++) {
            System.out.println("Hero heals" + heroAttackType[i] + " " + heroHeals[i] + " " + " damage " + heroDamage[i]);
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroAttackType.length; i++) {
            if (bossHeals > 0) {
                heroHeals[i] = heroHeals[i] - bossDamage;
                if (heroHeals[i] <= 0) {
                    heroHeals[i] = 0;
                }
            }
        }
    }

    public static void heroHits() {
        for (int i = 0; i < heroAttackType.length; i++) {
            if (heroHeals[i] > 0) {
                bossHeals = bossHeals - heroDamage[i];
                if (bossHeals <= 0) {
                    bossHeals = 0;
                }
            }
        }
    }

    public static void medic() {
        boolean aliveMedic = false;
        aliveMedic = false;
        for (int i = 0; i <heroAttackType.length; i++) {
            if (heroAttackType[i].equals("Medic") && heroHeals[i] > 0){
                aliveMedic = true;
            }
        }
        for (int i = 0; i < heroAttackType.length; i++) {
            if (heroHeals[i] > 0 && heroHeals[i] < 100 && aliveMedic){
                heroHeals[i] = heroHeals[i] + 50;
                System.out.println("Medic heals up " + heroAttackType[i]);
                break;
            }
        }

    }


    public static void round() {
        System.out.println("ROUND - " + round);
        bossHits();
        heroHits();
        bossTypeBarrier();
        medic();
        printStatistics();
    }
}
