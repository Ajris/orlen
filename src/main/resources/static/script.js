var map;
var jsonData;
var polygons;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 52.587711, lng: 19.669549},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDefaultUI: true
    });

    jsonData.forEach(marker => {
        var myLatlng = new google.maps.LatLng(marker.coordinates[1], marker.coordinates[0]);
        var newMarker = new google.maps.Marker({
            position: myLatlng,
            title: 'Hello World!'
        });
        newMarker.setMap(map);
    });

    for(var i=0; i<polygons.length; ++i) {
        console.log(polygons[i]);
        var flightPath = new google.maps.Polyline({
            path: [{
                lng: polygons[i].longtitude[0],
                lat: polygons[i].latitude[0]
            },
                {
                    lng: polygons[i].longtitude[1],
                    lat: polygons[i].latitude[1]
                }],
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 10,
            maxWidth: polygons[i].maxWidth,
            maxHeight: polygons[i].maxHeight

        });
        flightPath.setMap(map);
        google.maps.event.addListener(flightPath, 'click', function() {
            editRoute(this.maxWidth, this.maxHeight);
        });
    }
}

function findWay() {
    console.log('aaa');
}

function editRoute(maxWidth, maxHeight) {
    const editRoute = document.querySelector('.editRoute');
    const maxWidthPlaceholder = document.querySelector('.maxWidth');
    const maxHeightPlaceholder = document.querySelector('.maxHeight');
    editRoute.style.display = "block";
    maxWidthPlaceholder.placeholder = maxWidth;
    maxHeightPlaceholder.placeholder = maxHeight;
}

$.getJSON('markers.json', function(data) {
    jsonData = data;
});

$.getJSON('polygons.json', function(data) {
    polygons = data;
});