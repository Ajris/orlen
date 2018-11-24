
var map;
var jsonData;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 52.587711, lng: 19.669549},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    jsonData.forEach(marker => {
        var myLatlng = new google.maps.LatLng(marker.longitude, marker.latitude);
    var newMarker = new google.maps.Marker({
        position: myLatlng,
        title: 'Hello World!'
    });
    newMarker.setMap(map);
});
}


$.getJSON('./crossroads', function(data) {
    jsonData = data;
});