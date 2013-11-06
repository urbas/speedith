package speedith.core.reasoning.util.unitary;

import org.junit.Test;
import speedith.core.lang.PrimarySpiderDiagram;
import speedith.core.lang.Region;
import speedith.core.lang.Zone;
import speedith.core.lang.Zones;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static speedith.core.lang.SpiderDiagrams.createPrimarySD;

public class ZoneTransferTest {

    private static final ArrayList<Zone> vennABCZones = Zones.allZonesForContours("A", "B", "C");
    private static final ArrayList<Zone> vennABDZones = Zones.allZonesForContours("A", "B", "D");
    public static final PrimarySpiderDiagram vennABCDiagram = createPrimarySD(null, null, null, vennABCZones);
    public static final PrimarySpiderDiagram vennABDDiagram = createPrimarySD(null, null, null, vennABDZones);
    private static final ArrayList<Zone> intersectionAC = Zones.getZonesInsideAllContours(vennABCZones, "A", "C");
    public static final PrimarySpiderDiagram diagramABC_shadedSetAC = createPrimarySD(null, null, intersectionAC, vennABCZones);
    public static final PrimarySpiderDiagram diagramABC_shadedSetC_A = createPrimarySD(null, null, Zones.getZonesOutsideContours(Zones.getZonesInsideAnyContour(vennABCZones, "A", "C"), "A"), vennABCZones);

    private final PrimarySpiderDiagram diagramWithABZones = getDiagramWithABZones();
    private final PrimarySpiderDiagram diagramWithBCZones = getDiagramWithBCZones();

    @Test
    public void contoursOnlyInSource_returns_the_contour_that_is_present_in_the_source_diagram_but_not_in_the_destination_diagram() throws Exception {
        ZoneTransfer zoneTransfer = new ZoneTransfer(diagramWithABZones, diagramWithBCZones);
        assertThat(
                zoneTransfer.contoursOnlyInSource(),
                contains("A")
        );
    }

    @Test
    public void contoursOnlyInSource_returns_an_empty_set_when_the_source_diagram_has_no_contours() throws Exception {
        ZoneTransfer zoneTransfer = new ZoneTransfer(createPrimarySD(), diagramWithBCZones);
        assertThat(
                zoneTransfer.contoursOnlyInSource(),
                hasSize(0)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void destinationZonesForSourceContour_should_throw_an_exception_if_the_contour_is_not_in_the_source_diagram() {
        new ZoneTransfer(diagramWithABZones, diagramWithBCZones).zonesInDestinationOutsideContour("D");
    }

    @Test(expected = IllegalArgumentException.class)
    public void destinationZonesForSourceContour_should_throw_an_exception_if_the_contour_is_in_the_destination_diagram() {
        new ZoneTransfer(diagramWithABZones, diagramWithBCZones).zonesInDestinationOutsideContour("B");
    }

    @Test
    public void zonesInDestinationOutsideContour_should_return_an_empty_set_for_two_Venn_diagrams() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(vennABCDiagram, vennABDDiagram);
        assertThat(
                zoneTransfer.zonesInDestinationOutsideContour("C"),
                hasSize(0)
        );
    }

    @Test
    public void zonesInDestinationOutsideContour_should_return_all_zones_inside_A_when_A_is_disjoint_to_C() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(diagramABC_shadedSetAC, vennABDDiagram);
        assertThat(
                zoneTransfer.zonesInDestinationOutsideContour("C"),
                containsInAnyOrder(Zones.getZonesInsideAllContours(vennABDZones, "A").toArray())
        );
    }

    @Test
    public void zonesInDestinationOutsideContour_should_return_all_zones_outside_A_when_A_is_contains_C() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(diagramABC_shadedSetC_A, vennABDDiagram);
        assertThat(
                zoneTransfer.zonesInDestinationOutsideContour("C"),
                containsInAnyOrder(Zones.getZonesOutsideContours(vennABDZones, "A").toArray())
        );
    }

    @Test
    public void zonesInDestinationOutsideContour_when_using_diagrams_from_speedith_paper_should_return_zones_outside_E() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(getDiagramSpeedithPaperD2(), getDiagramSpeedithPaperD1());
        assertThat(
                zoneTransfer.zonesInDestinationOutsideContour("E"),
                containsInAnyOrder(
                        Zone.fromInContours("B").withOutContours("A", "D", "C"),
                        Zone.fromInContours("B", "D").withOutContours("A", "C"),
                        Zone.fromInContours("D").withOutContours("A", "B", "C"),
                        Zone.fromOutContours("A", "C", "B", "D")
                )
        );
    }

    @Test
    public void zonesInDestinationInsideContour_should_return_an_empty_region_in_a_venn2_diagram() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(vennABCDiagram, vennABDDiagram);
        assertThat(
                zoneTransfer.zonesInDestinationInsideContour("C"),
                hasSize(0)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void zonesInDestinationInsideContour_should_throw_an_exception_if_the_contour_is_not_only_in_the_source_diagram() {
        new ZoneTransfer(vennABCDiagram, vennABDDiagram).zonesInDestinationInsideContour("A");
    }
    @Test
    public void zonesInDestinationInsideContour_should_return_an_empty_region_in_a_venn2_diagram1() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(vennABCDiagram, vennABDDiagram);
        assertThat(
                zoneTransfer.zonesInDestinationInsideContour("C"),
                hasSize(0)
        );
    }
    @Test
    public void zonesInDestinationInsideContour_should_return_zones_inside_A() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(getDiagramABCWhereCContainsA(), vennABDDiagram);
        assertThat(
                zoneTransfer.zonesInDestinationInsideContour("C"),
                containsInAnyOrder(
                        Zone.fromInContours("A").withOutContours("B", "D"),
                        Zone.fromInContours("A", "B").withOutContours("D"),
                        Zone.fromInContours("A", "D").withOutContours("B"),
                        Zone.fromInContours("A", "B", "D").withOutContours()
                )
        );
    }

    @Test
    public void zonesInDestinationInsideContour_when_transfering_contour_E_in_diagrams_from_speedith_paper_should_return_zones_inside_AC() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(getDiagramSpeedithPaperD2(), getDiagramSpeedithPaperD1());
        assertThat(
                zoneTransfer.zonesInDestinationInsideContour("E"),
                containsInAnyOrder(
                        Zone.fromInContours("A", "C").withOutContours("B", "D")
                )
        );
    }

    @Test
    public void zonesInDestinationInsideContour_when_transfering_contour_E_in_a_diagram_where_E_contains_A_and_C_should_return_all_zones_inside_A() {
        ZoneTransfer zoneTransfer = new ZoneTransfer(getDiagramSpeedithPaperD2("E", "A"), getDiagramSpeedithPaperD1());
        assertThat(
                zoneTransfer.zonesInDestinationInsideContour("E"),
                containsInAnyOrder(
                        Zone.fromInContours("A", "C").withOutContours("B", "D"),
                        Zone.fromInContours("A", "D").withOutContours("B", "C"),
                        Zone.fromInContours("A").withOutContours("B", "C", "D")
                )
        );
    }

    public static PrimarySpiderDiagram getDiagramSpeedithPaperD2() {
        return getDiagramSpeedithPaperD2("A", "E");
    }

    public static PrimarySpiderDiagram getDiagramSpeedithPaperD2(String outsideContour, String insideContour) {
        String contourC = "C";
        String contourF = "F";
        ArrayList<Zone> abcdPowerRegion = Zones.allZonesForContours(outsideContour, contourF, contourC, insideContour);
        ArrayList<Zone> shaded_E_A = Zones.getZonesInsideAllContours(Zones.getZonesOutsideContours(abcdPowerRegion, outsideContour), insideContour);
        ArrayList<Zone> shaded_C = Zones.getZonesInsideAllContours(abcdPowerRegion, contourC);
        ArrayList<Zone> shaded_F_ACE = Zones.getZonesInsideAnyContour(Zones.getZonesInsideAllContours(abcdPowerRegion, contourF), outsideContour, contourC, insideContour);

        TreeSet<Zone> presentZones = new TreeSet<>(abcdPowerRegion);
        presentZones.removeAll(shaded_E_A);
        presentZones.removeAll(shaded_C);
        presentZones.removeAll(shaded_F_ACE);

        TreeSet<Zone> shadedZones = new TreeSet<>();
        shadedZones.addAll(shaded_E_A);
        shadedZones.addAll(shaded_C);
        shadedZones.addAll(shaded_F_ACE);

        TreeMap<String, Region> habitats = new TreeMap<>();
        habitats.put("s1", new Region(Zone.fromInContours(outsideContour, insideContour, contourC).withOutContours(contourF)));
        habitats.put("s2", new Region(
                Zone.fromInContours(contourF).withOutContours(outsideContour, contourC, insideContour),
                Zone.fromOutContours(outsideContour, contourC, insideContour, contourF)
        ));
        habitats.put("s3", new Region(
                Zone.fromInContours(contourF).withOutContours(outsideContour, contourC, insideContour),
                Zone.fromOutContours(outsideContour, contourC, insideContour, contourF)
        ));

        return createPrimarySD(habitats.keySet(), habitats, shadedZones, presentZones);
    }

    private PrimarySpiderDiagram getDiagramWithBCZones() {
        return createPrimarySD(null, null, null, asList(Zone.fromOutContours("B", "C")));
    }

    private PrimarySpiderDiagram getDiagramWithABZones() {
        return createPrimarySD(null, null, null, asList(Zone.fromInContours("A", "B")));
    }

    public static PrimarySpiderDiagram getDiagramSpeedithPaperD1() {
        ArrayList<Zone> abcdPowerRegion = Zones.allZonesForContours("A", "B", "C", "D");
        ArrayList<Zone> shaded_C_A = Zones.getZonesInsideAllContours(Zones.getZonesOutsideContours(abcdPowerRegion, "A"), "C");
        ArrayList<Zone> shaded_CBD = Zones.getZonesInsideAnyContour(Zones.getZonesInsideAnyContour(abcdPowerRegion, "B", "D"), "C");
        ArrayList<Zone> shaded_AB = Zones.getZonesInsideAllContours(abcdPowerRegion, "A", "B");

        TreeSet<Zone> presentZones = new TreeSet<>(abcdPowerRegion);
        presentZones.removeAll(shaded_C_A);
        presentZones.removeAll(shaded_CBD);
        presentZones.removeAll(shaded_AB);
        presentZones.removeAll(shaded_AB);

        TreeSet<Zone> shadedZones = new TreeSet<>(shaded_C_A);
        shadedZones.addAll(shaded_CBD);
        shadedZones.addAll(shaded_AB);

        TreeMap<String, Region> habitats = new TreeMap<>();
        habitats.put("s1", new Region(Zone.fromInContours("A", "C").withOutContours("B", "D")));
        habitats.put("s2", new Region(
                Zone.fromInContours("B", "D").withOutContours("A", "C"),
                Zone.fromInContours("D").withOutContours("B", "A", "C"),
                Zone.fromInContours("B").withOutContours("D", "A", "C"),
                Zone.fromOutContours("B", "D", "A", "C")
        ));

        return createPrimarySD(habitats.keySet(), habitats, shadedZones, presentZones);
    }

    public static PrimarySpiderDiagram getDiagramABCWhereCContainsA() {
        ArrayList<Zone> zonesInsideAC_outsideC = Zones.getZonesOutsideContours(Zones.getZonesInsideAnyContour(vennABCZones, "A", "C"), "C");

        TreeSet<Zone> presentZones = new TreeSet<>(vennABCZones);
        presentZones.removeAll(zonesInsideAC_outsideC);

        return createPrimarySD(null, null, zonesInsideAC_outsideC, presentZones);
    }
}
