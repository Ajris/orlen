
var map;
var jsonData;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 52.587711, lng: 19.669549},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    jsonData.markers.forEach(marker => {
        var myLatlng = new google.maps.LatLng(marker.coordinates[1], marker.coordinates[0]);
    var newMarker = new google.maps.Marker({
        position: myLatlng,
        title: 'Hello World!'
    });
    newMarker.setMap(map);
});
}

$.getJSON('markers.json', function(data) {
    jsonData = data;
});