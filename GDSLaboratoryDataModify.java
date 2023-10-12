import java.util.HashMap;
import java.util.Map;

public class GDSLaboratoryDataModify {

    private static final Map<String, String> MAPPING = new HashMap<>();

    static {
        MAPPING.put("GOT", "\n[ GOT - GPT - ALP - GGT - T-Bil - Alb ]");
        MAPPING.put("T-Chol", "\n[T-Chol - HDL - Triglyceride - LDL ];");
        MAPPING.put("HGB", "\n[ Hb - WBC - Platelet];");
        MAPPING.put("Glucose", "\n[Glucose - HbA1c ];");
        MAPPING.put("free T-4", "\n[ T3 - free-T4 - TSH ];");
    }

    public static void main(String textFromInputArea) {
        for (Map.Entry<String, String> entry : MAPPING.entrySet()) {
            if (textFromInputArea.contains(entry.getKey())) {
                GDSLaboratoryDataGUI.appendTextAreas(entry.getValue());
            }
        }
    }
}
