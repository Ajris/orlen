var map;
var markers;
var polylines;

window.onload = function() {
    generate();
}

function generate() {
    setTimeout(function() {
        $.getJSON('crossroads', function(data) {
            markers = data;
            $.getJSON('roads', function(data) {
                polylines = data;
                initiateMaps();
            });
        });
    },500);
    closeEditMarker();
    closeEditRoad();
}

function initiateMaps() {
    let mapOptions =  {
        center: {lat: 52.587711, lng: 19.669549},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDefaultUI: true
    }

    map = new google.maps.Map(document.getElementById('map'), mapOptions);
    generateMarkers();
    generatePolylines();
    addMarkerList();
}

function generateMarkers() {
    markers.forEach(marker => {
        var icon = {
            url: "./dot.png",
            scaledSize: new google.maps.Size(20, 20),
            origin: new google.maps.Point(0,0),
            anchor: new google.maps.Point(10, 10)
        };

    var markerOptions = {
        position: new google.maps.LatLng(marker.longitude, marker.latitude),
        id: marker.id,
        draggable: true,
        icon: icon
    };

    var newMarker = new google.maps.Marker(markerOptions);
    google.maps.event.addListener(newMarker, 'dragend', function() {
        sendNewMarkerInfo(this);
    });
    google.maps.event.addListener(newMarker, 'click', function() {
        editMarker(this.id, this.position.lat(), this.position.lng());
    });
    newMarker.setMap(map);
});
}

function generatePolylines() {
    polylines.forEach(polyline => {
        var polylineOptions = {
            path: [{
                lng: polyline.start.latitude,
                lat: polyline.start.longitude
            },
                {
                    lng: polyline.end.latitude,
                    lat: polyline.end.longitude
                }],
            geodesic: true,
            strokeColor: '#005489',
            strokeOpacity: 1.0,
            strokeWeight: 7,
            id: polyline.id,
            maxWidth: polyline.width,
            maxHeight: polyline.height
        }

        var newRoad = new google.maps.Polyline(polylineOptions);
    newRoad.setMap(map);
    google.maps.event.addListener(newRoad, 'click', function() {
        editRoute(this.maxWidth, this.maxHeight, this.id);
    });
});
}

function editMarker(id, lat, lng) {
    const editMarker = document.querySelector('.editMarker');
    const idPlaceholder = document.querySelector('.markerId');
    const latPlaceholder = document.querySelector('.lat');
    const lngPlaceholder = document.querySelector('.lng');
    editMarker.style.display = "block";
    idPlaceholder.value = id;
    latPlaceholder.value = lat;
    lngPlaceholder.value = lng;
    closeEditRoad();
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
    closeEditMarker();
}

function closeEditRoad() {
    const editRoute = document.querySelector('.editRoute');
    editRoute.style.display = "none";
}

function closeEditMarker() {
    const editMarker = document.querySelector('.editMarker');
    editMarker.style.display = "none";
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
    generate();
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
    generate();
}

function deleteRoad() {
    var routeId = document.querySelector('.routeId');
    var data ={
        "id": routeId.value
    };
    $.ajax({
        url: 'deleteRoute',
        type: 'PUT',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
    generate();
}

function addMarkerList() {
    const list = document.querySelector('.list');
    markers.forEach(marker => {
        var newNode = document.createElement("p");
    newNode.innerHTML = marker.title;
    list.appendChild(newNode);
});
}