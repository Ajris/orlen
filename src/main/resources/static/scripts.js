var map;
var jsonData;
var polygons;
function initiation() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 52.587711, lng: 19.669549},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDefaultUI: true
    });

    jsonData.forEach(marker => {
        var myLatlng = new google.maps.LatLng(marker.longitude, marker.latitude);
        var newMarker = new google.maps.Marker({
            position: myLatlng,
            title: 'Hello World!',
            draggable: true
        });
        newMarker.setMap(map);
    });

    for(var i=0; i<polygons.length; i++) {
        var flightPath = new google.maps.Polyline({
            path: [{
                lng: polygons[i].start.latitude,
                lat: polygons[i].start.longitude
            },
                {
                    lng: polygons[i].end.latitude,
                    lat: polygons[i].end.longitude
                }],
            geodesic: true,
            strokeColor: '#005489',
            strokeOpacity: 1.0,
            strokeWeight: 7,
            maxWidth: polygons[i].width,
            maxHeight: polygons[i].height

        });
        flightPath.setMap(map);
        google.maps.event.addListener(flightPath, 'click', function() {
            editRoute(this.maxWidth, this.maxHeight);
        });
    }
    addAll();
}

function findWay() {
    console.log('aaa');
}

function addAll() {
    const list = document.querySelector('.list');
    jsonData.forEach(marker => {
        console.log(marker);
        var newNode = document.createElement("p");
        newNode.innerHTML = marker.title;
        list.appendChild(newNode);
    });
}

function closeEditRoad() {
    const editRoute = document.querySelector('.editRoute');
    editRoute.style.display = "none";
}

function editRoute(maxWidth, maxHeight) {
    const editRoute = document.querySelector('.editRoute');
    const maxWidthPlaceholder = document.querySelector('.maxWidth');
    const maxHeightPlaceholder = document.querySelector('.maxHeight');
    editRoute.style.display = "block";
    maxWidthPlaceholder.value = maxWidth;
    maxHeightPlaceholder.value = maxHeight;
}

$.getJSON('crossroads', function(data) {
    jsonData = data;
    $.getJSON('roads', function(data) {
        polygons = data;
        initiation();
    });
});