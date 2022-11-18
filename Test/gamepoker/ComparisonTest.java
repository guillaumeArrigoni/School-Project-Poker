package gamepoker;

import gamepoker.exception.PokerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparisonTest {

    private static Card neufPi;
    private static Card dixPi;
    private static Card valetPi;
    private static Card damePi;
    private static Card roiPi;
    private static Card cinqCo;
    private static Card sixCo;
    private static Card septCo;
    private static Card huitCo;
    private static Card neufCo;
    private static Card deuxPi;
    private static Card quatrePi;
    private static Card roiCo;
    private static Card roiCa;
    private static Card valetCo;
    private static Card valetCa;
    private static Card dameTr;
    private static Card roiTr;
    private static Card valetTr;
    private static HandPoker straightFlushPi;
    private static HandPoker straightFlushCo;
    private static HandPoker flushPi;
    private static HandPoker straightRoi;
    private static HandPoker flushCo;
    private static HandPoker straightNeuf;
    private static HandPoker carreRoi;
    private static HandPoker carreValet;
    private static HandPoker brelanRoi;
    private static HandPoker brelanValet;
    private static HandPoker doublePaireRoiNeuf;
    private static HandPoker doublePairDameValet;
    private static HandPoker pairRoi;
    private static HandPoker pairValet;
    private static HandPoker higherCardRoi;
    private static HandPoker higherCardDame;
    private static Comparison comparisonStraightFlushE;
    private static Comparison comparisonFlushE;
    private static Comparison comparisonStraightE;
    private static Comparison comparisonCarreE;
    private static Comparison comparisonBrelanE;
    private static Comparison comparisonDoublePairE;
    private static Comparison comparisonPairE;
    private static Comparison comparisonHigherCardE;
    private static Comparison comparisonStraightFlushW;
    private static Comparison comparisonCarreW;
    private static Comparison comparisonFlushW;
    private static Comparison comparisonStraightW;
    private static Comparison comparisonBrelanW;
    private static Comparison comparisonDoublePairW;
    private static Comparison comparisonPairW;

    @BeforeAll
    @Order(1)
    public static void setupCard() throws PokerException {
        neufPi = new Card("9Pi");
        dixPi = new Card("10Pi");
        valetPi = new Card("VPi");
        damePi = new Card("DPi");
        roiPi = new Card("RPi");
        cinqCo = new Card("5Co");
        sixCo = new Card("6Co");
        septCo = new Card("7Co");
        huitCo = new Card("8Co");
        neufCo = new Card("9Co");
        deuxPi = new Card("2Pi");
        quatrePi = new Card("4Pi");
        roiCo = new Card("RCo");
        roiCa = new Card("RCa");
        valetCo = new Card("VCo");
        valetCa = new Card("VCa");
        valetTr = new Card("VTr");
        roiTr = new Card("RTr");
        dameTr = new Card("DTr");
    }

    @BeforeAll
    @Order(2)
    public static void setupHandPoker() {
        straightFlushPi = new HandPoker(new ArrayList<>(Arrays.asList(neufPi, dixPi, valetPi, damePi, roiPi)));
        straightFlushCo = new HandPoker(new ArrayList<>(Arrays.asList(cinqCo, sixCo, septCo, huitCo, neufCo)));
        flushPi = new HandPoker(new ArrayList<>(Arrays.asList(deuxPi, quatrePi, neufPi, dixPi, valetPi)));
        flushCo = new HandPoker(new ArrayList<>(Arrays.asList(cinqCo, septCo, huitCo, roiCo, valetCo)));
        straightRoi = new HandPoker(new ArrayList<>(Arrays.asList(neufPi, dixPi, valetCa, damePi, roiCo)));
        straightNeuf = new HandPoker(new ArrayList<>(Arrays.asList(cinqCo, sixCo, septCo, huitCo, neufPi)));
        carreRoi = new HandPoker(new ArrayList<>(Arrays.asList(roiCo, roiCa, roiPi, roiTr, deuxPi)));
        carreValet = new HandPoker(new ArrayList<>(Arrays.asList(valetCo, valetTr, valetCa, valetPi, neufPi)));
        brelanRoi = new HandPoker(new ArrayList<>(Arrays.asList(roiPi, roiCa, roiTr, cinqCo, huitCo)));
        brelanValet = new HandPoker(new ArrayList<>(Arrays.asList(roiTr, valetPi, valetCa, valetCo, sixCo)));
        doublePaireRoiNeuf = new HandPoker(new ArrayList<>(Arrays.asList(roiCa, roiTr, neufPi, neufCo, deuxPi)));
        doublePairDameValet = new HandPoker(new ArrayList<>(Arrays.asList(dameTr, damePi, valetCo, valetTr, septCo)));
        pairRoi = new HandPoker(new ArrayList<>(Arrays.asList(roiTr, roiCa, dixPi, neufCo, cinqCo)));
        pairValet = new HandPoker(new ArrayList<>(Arrays.asList(valetTr, valetCa, dameTr, septCo, sixCo)));
        higherCardRoi = new HandPoker(new ArrayList<>(Arrays.asList(septCo, huitCo, neufCo, dameTr, roiPi)));
        higherCardDame = new HandPoker(new ArrayList<>(Arrays.asList(damePi, cinqCo, dixPi, neufCo, valetCa)));
    }

    @BeforeAll
    @Order(3)
    public static void setupComparison() {
        comparisonStraightFlushE = new Comparison(straightFlushPi, straightFlushCo);
        comparisonCarreE = new Comparison(carreRoi, carreValet);
        comparisonFlushE = new Comparison(flushCo, flushPi);
        comparisonStraightE = new Comparison(straightRoi, straightNeuf);
        comparisonBrelanE = new Comparison(brelanRoi, brelanValet);
        comparisonDoublePairE = new Comparison(doublePaireRoiNeuf, doublePairDameValet);
        comparisonPairE = new Comparison(pairRoi, pairValet);
        comparisonHigherCardE = new Comparison(higherCardRoi, higherCardDame);
        comparisonStraightFlushW = new Comparison(straightRoi, brelanValet);
        comparisonCarreW = new Comparison(carreValet, doublePairDameValet);
        comparisonFlushW = new Comparison(flushCo, pairValet);
        comparisonStraightW = new Comparison(straightRoi, brelanRoi);
        comparisonBrelanW = new Comparison(brelanValet, doublePaireRoiNeuf);
        comparisonDoublePairW = new Comparison(doublePairDameValet, pairRoi);
        comparisonPairW = new Comparison(pairValet, higherCardRoi);
    }

    private static Stream<Arguments> provideComparison(){
        return Stream.of(
                Arguments.of(comparisonBrelanE),
                Arguments.of(comparisonCarreE),
                Arguments.of(comparisonFlushE),
                Arguments.of(comparisonDoublePairE),
                Arguments.of(comparisonPairE),
                Arguments.of(comparisonHigherCardE),
                Arguments.of(comparisonStraightE),
                Arguments.of(comparisonStraightFlushE),
                Arguments.of(comparisonStraightFlushW),
                Arguments.of(comparisonCarreW),
                Arguments.of(comparisonFlushW),
                Arguments.of(comparisonStraightW),
                Arguments.of(comparisonBrelanW),
                Arguments.of(comparisonDoublePairW),
                Arguments.of(comparisonPairW)
                );
    }

    @ParameterizedTest
    @MethodSource("provideComparison")
    void getWinning(Comparison comparison) {
        assertTrue(comparison.getWinning().get());
    }


    @Test
    void getWinningValue() {
        //TODO : Finish this
    }

    @Test
    void getWinningCombination() {
        //TODO : Finish this
    }

    //TODO : Finish test
}