package resources;

import pojo.AddPlaceAPIRequest;
import pojo.LocationRequest;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlaceAPIRequest addPlacePaylod(String name, String language, String address){
        AddPlaceAPIRequest addPlaceAPIRequest = new AddPlaceAPIRequest();
        addPlaceAPIRequest.setAccuracy(50);
        addPlaceAPIRequest.setAddress(address);
        addPlaceAPIRequest.setLanguage(language);
        addPlaceAPIRequest.setPhone_number("345678");
        addPlaceAPIRequest.setWebsite("https://raghuveer.com");
        addPlaceAPIRequest.setName(name);
        List<String> list = new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        addPlaceAPIRequest.setTypes(list);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setLat(-38.383494);
        locationRequest.setLng(33.427362);
        addPlaceAPIRequest.setLocation(locationRequest);
        return addPlaceAPIRequest;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }
}
