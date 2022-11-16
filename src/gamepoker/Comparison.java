package gamepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**NOTE
 * for the combination : color :
 * for the test when both hand have color :
 * reuse the chooseWinningSingle (just add the check if all the card have the same color)
 */

public class Comparison {
    /**
     * THE WINNING COMBINATION
     * winningCombination give the number associate to the combination that is winning
     * The value are :
     * Equality : 0
     * Single : 1
     * Pair : 2
     * Double pair : 3
     * Brelan : 4
     * Suite : 5
     * Color : 6
     * Full : 7
     * Carre : 8
     * Quinte Flush : 9
     * .....
     *
     * THE VALUE ASSOCIATE TO THE COMBINATION IN THE DICO
     * Single : 1
     * Pair : 2
     * Brelan : 3
     * Carre : 4
     */
    public static final int EQUALITY = 0;
    public static final int SINGLE_FOR_DICO_KEY = 1;
    public static final int PAIR_FOR_DICO_KEY = 2;
    public static final int BRELAN_FOR_DICO_KEY = 3;
    private static final int CARRE_FOR_DICO_KEY = 4 ;

    public static final int SINGLE_FOR_COMBINATION = 1;
    public static final int PAIR_FOR_COMBINATION = 2;
    public static final int TWO_PAIR_FOR_COMBINAISON = 3;
    public static final int BRELAN_FOR_COMBINATION = 4;
    public static final int SUITE_FOR_COMBINAISON = 5;
    public static final int COLOR_FOR_COMBINAISON = 6;
    public static final int FULL_FOR_COMBINAISON = 7;
    private static final int CARRE_FOR_COMBINATION=8;
    private static final int QUITE_FLUSH_FOR_COMBINAISON = 9;
    private final HashMap<Integer, String> correspondanceCombinaisonEntierString = new HashMap<>();
    private int winningCombination;
    private ArrayList<Integer> winningValue;
    private Boolean firstWinningOnSecond;
    private HandPoker hand1;
    private HandPoker hand2;

    public Comparison(HandPoker firstHand, HandPoker secondHand) {
        initDictionary();
        this.hand1 = firstHand;
        this.hand2 = secondHand;
        this.firstWinningOnSecond = chooseWinningHand();
    }

    @Override
    public String toString() {
        String texte = correspondanceCombinaisonEntierString.get(winningCombination);
        if (winningCombination != 0){
            texte = texte + " of ";
        }
        Value value = new Value(winningValue.get(0));
        texte = texte + value.toString();
        for (int i = 1; i<winningValue.size();i++){ /* dans le cas d'une doube pair ou d'une couleur par exemple*/
            texte = texte + ", ";
            Value valueMore = new Value(winningValue.get(i));
            texte = texte + valueMore.toString();
        }
        return texte;
    }

    private void initDictionary(){
        correspondanceCombinaisonEntierString.put(1, "the highest card : ");
        correspondanceCombinaisonEntierString.put(2, "a pair");
        correspondanceCombinaisonEntierString.put(3, "a double pair");
        correspondanceCombinaisonEntierString.put(4, "a three of a kind");
        correspondanceCombinaisonEntierString.put(5, "a straight");
        correspondanceCombinaisonEntierString.put(6, "a flush");
        correspondanceCombinaisonEntierString.put(7, "a full");
        correspondanceCombinaisonEntierString.put(8, "a four of a kind");
        correspondanceCombinaisonEntierString.put(9, "a quinte flush");
    }
    public Boolean getWinning() {
        return this.firstWinningOnSecond;
    }
    public List<Integer> getWinningValue() {
        return this.winningValue;
    }
    public int getWinningCombination() {
        return this.winningCombination;
    }

    private boolean haveSingle(HandPoker hand) {return hand.getHandOccurrence().containsValue(SINGLE_FOR_DICO_KEY);}
    private boolean havePair(HandPoker hand) { return hand.getHandOccurrence().containsValue(PAIR_FOR_DICO_KEY); }
    private boolean haveBrelan(HandPoker hand) {
        return hand.getHandOccurrence().containsValue(BRELAN_FOR_DICO_KEY);
    }
    private boolean haveCarre(HandPoker hand) {
        return hand.getHandOccurrence().containsValue(CARRE_FOR_DICO_KEY);
    }
    private boolean haveDoublePair(HandPoker hand) {
        if (hand.getHandCombination().get(PAIR_FOR_DICO_KEY).size() == 2){
            return true;
        } else {
            return false;
        }
    }
    private boolean haveSuite(HandPoker hand){
        if (hand.getHandCombination().get(SINGLE_FOR_DICO_KEY).size() == 5){
            int difference = getSingle(hand).get(0) - getSingle(hand).get(4);
            if (difference == 4){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Integer> getSingle(HandPoker hand) {
        ArrayList<Integer> list = hand.getHandCombination().get(SINGLE_FOR_DICO_KEY);
        list.sort(Collections.reverseOrder());
        return list;
    }
    private ArrayList<Integer> getPair(HandPoker hand) {
        ArrayList<Integer> list = hand.getHandCombination().get(PAIR_FOR_DICO_KEY);
        list.sort(Collections.reverseOrder());
        return list;
    }
    private Integer getBrelan(HandPoker hand) {return hand.getHandCombination().get(BRELAN_FOR_DICO_KEY).get(0); /* only one brelan can be reach in any hand -> list of 1 element*/}
    private Integer getCarre(HandPoker hand) {return hand.getHandCombination().get(CARRE_FOR_DICO_KEY).get(0);}



    /**
     * return the number to the higher combination
     * Single card : 1
     * Pair : 2
     * Double pair : 3
     * Brelan : 4
     * Suite : 5
     * Color : 6
     * Full : 7
     * Carre : 8
     * Quinte Flush : 9
     *
     * @return the number of the higher combination
     */
    private int chooseCombination(HandPoker hand) {
        if (haveCarre(hand)) {
            return CARRE_FOR_COMBINATION;
        } else if (haveSuite(hand)){
            return SUITE_FOR_COMBINAISON;
        } else if (haveBrelan(hand)) {
            return BRELAN_FOR_COMBINATION;
        } else if (havePair(hand)) {
            return doublePairOrUniquePair(hand);
        } else {

            return SINGLE_FOR_COMBINATION;
        }
    }

    private int doublePairOrUniquePair(HandPoker hand){
        if (haveDoublePair(hand)){
            return TWO_PAIR_FOR_COMBINAISON;
        } else {
            return PAIR_FOR_COMBINATION;
        }
    }

    /**
     * @return (true, false or null) if the first hand is (better, worst or equals) to the second hand
     */
    private Boolean chooseWinningHand() {
        if (chooseCombination(this.hand1) == chooseCombination(this.hand2)) {
            switch (chooseCombination(this.hand1)){
                case CARRE_FOR_COMBINATION:
                    if (chooseWinningCarre()==null){
                        return chooseWinningSingle();
                    }
                    else {
                        return chooseWinningCarre();
                    }

                case SUITE_FOR_COMBINAISON:
                    if (chooseWinningSingle()==null){
                        return null; /*égalité*/
                    } else {
                        Boolean result = chooseWinningSingle();
                        this.winningCombination = SUITE_FOR_COMBINAISON; /*modifier après l'appel de chooseWinningSingle pour mettre Suite comme combinaison gagnante et non pas "carte la plus haute"*/
                        return result;
                    }

                case BRELAN_FOR_COMBINATION :
                    if (chooseWinningBrelan() == null) {
                        return chooseWinningSingle(); /*car si la deuxième plus haute combinaison est une pair alors il s'agit d'un full et non un brelan*/
                    } else {
                        return chooseWinningBrelan();
                    }

                case PAIR_FOR_COMBINATION: case TWO_PAIR_FOR_COMBINAISON:
                    if (chooseWinningPair() == null) {
                        return chooseWinningSingle();
                    } else {
                        return chooseWinningPair();
                    }
                default: /*when case equals to SINGLE8FOR8COMBINATION*/
                    return chooseWinningSingle();
            }
        } else {
            return chooseWinningCombination();
        }
    }

    /**
     * return the value according to the winningCombination
     *
     * @return an ArrayList that contain all the value that give the winningCombination
     */
    private ArrayList<Integer> correspondenceValueComposition(int number, HandPoker hand) {
        ArrayList<Integer> listValue = new ArrayList<>();
        switch (number){
            case CARRE_FOR_COMBINATION :
                listValue.add(getCarre(hand)); /* car getCarre ne renvoie pas une liste */
                return listValue;
            case SUITE_FOR_COMBINAISON:
                listValue.add(getSingle(hand).get(0));
                return listValue;
            case BRELAN_FOR_COMBINATION:
                listValue.add(getBrelan(hand)); /* car getBrelan ne renvoie pas une liste */
                return listValue;
            default :
                return getPair(hand);
        }
    }

    /**
     * return a Boolean to know the winning combination between 2 hands
     * Update winningCombination and winningValue
     *
     * @return (true, false or null) if the first hand have a (higher, lower or equals) combination to the second hand
     */
    private Boolean chooseWinningCombination() {
        if (chooseCombination(this.hand1) > chooseCombination(this.hand2)) {
            this.winningCombination = chooseCombination(this.hand1);
            this.winningValue = correspondenceValueComposition(this.winningCombination, this.hand1);
            return true;
        } else if (chooseCombination(this.hand1) < chooseCombination(this.hand2)) {
            this.winningCombination = chooseCombination(this.hand2);
            this.winningValue = correspondenceValueComposition(this.winningCombination, this.hand2);
            return false;
        } else {
            return null;
        }
    }

    /**
     * return a Boolean to know the winning brelan between 2 brelan
     * Update winningCombination and winningValue
     *
     * @return (true, false or null) if the first brelan is (higher, lower or equals) to the second brelan
     */
    private Boolean chooseWinningBrelan() {
        if (getBrelan(this.hand1) > getBrelan(this.hand2)) {
            this.winningValue = new ArrayList<>(Collections.singletonList(getBrelan(this.hand1)));
            this.winningCombination = BRELAN_FOR_COMBINATION;
            return true;
        } else if (getBrelan(this.hand1) < getBrelan(this.hand2)) {
            this.winningValue = new ArrayList<>(Collections.singletonList(getBrelan(this.hand2)));
            this.winningCombination =  BRELAN_FOR_COMBINATION;
            return false;
        } else {
            return null;
        }
    }

    /**
     * return a Boolean to know the winning pair between 2 pairs
     * Update winningCombination and winningValue
     * @return (true, false or null) if the first pair is (higher, lower or equals) to the second pair
     */
    private Boolean chooseWinningPair() {
        ArrayList<Integer> thisPairSorted = getPair(this.hand1);
        ArrayList<Integer> handPairSorted = getPair(this.hand2);
        thisPairSorted.sort(Collections.reverseOrder()); /* trier par ordre decroissant pour comparer en premier les plus hautes paires des deux mains*/
        handPairSorted.sort(Collections.reverseOrder());
        this.winningValue = new ArrayList<>();
        for (int i = 0; i < thisPairSorted.size(); i++) {
            if (thisPairSorted.get(i) == handPairSorted.get(i)) {
                this.winningValue.add(thisPairSorted.get(i));
            } else if (thisPairSorted.get(i) > handPairSorted.get(i)) {
                this.winningValue.add(thisPairSorted.get(i));
                this.winningCombination = doublePairOrUniquePair(this.hand1);
                return true;
            } else {
                this.winningValue.add(handPairSorted.get(i));
                this.winningCombination = doublePairOrUniquePair(this.hand2);
                return false;
            }
        }
        return null;
    }

    /**
     * return a Boolean to know the winning single card between 2 singles cards
     * Update winningCombination and winningValue
     * @return (true, false or null) if the first single card is (higher, lower or equals) to the second single card
     */
    private Boolean chooseWinningSingle() {
        ArrayList<Integer> thisSingleSorted = getSingle(this.hand1);
        ArrayList<Integer> handSingleSorted = getSingle(this.hand2);
        thisSingleSorted.sort(Collections.reverseOrder());
        handSingleSorted.sort(Collections.reverseOrder());

        for (int i = 0; i < (thisSingleSorted.size()); i++) {
            /*
            thisSingleSorted and handSingleSorted have the same size
             */
            if (thisSingleSorted.get(i) != handSingleSorted.get(i)) {
                if (thisSingleSorted.get(i) > handSingleSorted.get(i)) {
                    this.winningValue = new ArrayList<>(Collections.singletonList(thisSingleSorted.get(i)));
                    this.winningCombination = SINGLE_FOR_COMBINATION;
                    return true;
                } else {
                    this.winningValue = new ArrayList<>(Collections.singletonList(handSingleSorted.get(i)));
                    this.winningCombination = SINGLE_FOR_COMBINATION;
                    return false;
                }
            }
        }
        this.winningCombination = EQUALITY;
        return null;
    }

    private Boolean chooseWinningCarre() {
        if (getCarre(this.hand1) > getCarre(this.hand2)) {
            this.winningValue = new ArrayList<>(Collections.singletonList(getCarre(this.hand1)));
            this.winningCombination = CARRE_FOR_COMBINATION;
            return true;
        } else if (getCarre(this.hand1) < getCarre(this.hand2)) {
            this.winningValue = new ArrayList<>(Collections.singletonList(getCarre(this.hand2)));
            this.winningCombination = CARRE_FOR_COMBINATION;
            return false;
        } else {
            return null;
        }
    }


}


