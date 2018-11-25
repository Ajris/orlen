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
            id: marker.id,
            title: 'Hello World!',
            draggable: true
        });
        google.maps.event.addListener(newMarker, 'dragend', function() {
            sendNewMarkerInfo(this);
        });
        newMarker.setMap(map);
    });

    polygons.forEach(polygon => {
        var flightPath = new google.maps.Polyline({
            path: [{
                lng: polygon.start.latitude,
                lat: polygon.start.longitude
            },
                {
                    lng: polygon.end.latitude,
                    lat: polygon.end.longitude
                }],
            geodesic: true,
            strokeColor: '#005489',
            strokeOpacity: 1.0,
            strokeWeight: 7,
            id: polygon.id,
            maxWidth: polygon.width,
            maxHeight: polygon.height
        });
        flightPath.setMap(map);
        google.maps.event.addListener(flightPath, 'click', function() {
            editRoute(this.maxWidth, this.maxHeight, this.id);
        });
    });
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
function sendNewMarkerInfo(marker) {
    let data = {
        "id": marker.id,
        "latitude": marker.position.lng(),
        "longitude": marker.position.lat()
    };
    $.ajax({
        url: 'setCrossroad',
        type: 'PUT',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
}

function updateRoute() {
    var routeId = document.querySelector('.routeId');
    var maxWidth = document.querySelector('.maxWidth');
    var maxHeight = document.querySelector('.maxHeight');
    var data ={
        "id": routeId.value,
        "width": maxWidth.value,
        "height": maxHeight.value,
    };
    $.ajax({
        url: 'setroad',
        type: 'PUT',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
}

function closeEditRoad() {
    const editRoute = document.querySelector('.editRoute');
    editRoute.style.display = "none";
}

function editRoute(maxWidth, maxHeight, id) {
    const editRoute = document.querySelector('.editRoute');
    const idPlaceHolder = document.querySelector('.routeId');
    const maxWidthPlaceholder = document.querySelector('.maxWidth');
    const maxHeightPlaceholder = document.querySelector('.maxHeight');
    idPlaceHolder.value = id;
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