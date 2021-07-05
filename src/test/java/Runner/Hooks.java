    package Runner;


    import io.cucumber.java.Before;
    import stepdefnitaion.PlaceValidationStepdef;

    import java.io.IOException;

    public class Hooks {

        @Before("@DeletePlace")
        public void beforeScenario() throws IOException {
            PlaceValidationStepdef placeValidationStepdef = new PlaceValidationStepdef();
            if (PlaceValidationStepdef.placeID == null) {
                placeValidationStepdef.i_sent_the_request_for_add_place_with("Raghu", "Kannada", "dasdasdasdasdas");
                placeValidationStepdef.i_calls_with_http_request("AddPlaceAPI", "POST");
                placeValidationStepdef.verify_place_id_created_maps_to_using("Raghu", "getPlaceAPI");
            }
        }
    }
