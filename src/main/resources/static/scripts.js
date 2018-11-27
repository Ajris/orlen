var map;
var markers;
var polylines;
var allMarkers = [];

window.onload = function() {
    generate({lat: 52.587711, lng: 19.669549}, 14);
}

function generate(LngLat, Zoom) {
    setTimeout(function() {
        $.getJSON('crossroads', function(data) {
            markers = data;
            $.getJSON('roads', function(data) {
                polylines = data;
                initiateMaps(LngLat, Zoom);
            });
        });
    },500);
    closeEditMarker();
    closeEditRoad();
}

function initiateMaps(LngLat, Zoom) {
    let mapOptions =  {
        center: LngLat,
        zoom: Zoom,
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
    allMarkers.push(newMarker);
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
        url: 'crossroads',
        type: 'PUT',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
    generate({lat: map.getCenter().lat(), lng: map.getCenter().lng()}, map.getZoom());
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
        url: 'roads',
        type: 'PUT',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
    generate({lat: map.getCenter().lat(), lng: map.getCenter().lng()}, map.getZoom());
}

function deleteRoad() {
    var routeId = document.querySelector('.routeId');
    $.ajax({
        url: 'roads/'+routeId.value,
        type: 'DELETE',
        contentType:'application/json',
        dataType:'json'
    });
    generate({lat: map.getCenter().lat(), lng: map.getCenter().lng()}, map.getZoom());
}

function addMarkerList() {
    const list = document.querySelector('.list');
    markers.forEach(marker => {
        var newNode = document.createElement("p");
    newNode.innerHTML = marker.title;
    list.appendChild(newNode);
});
}

function deleteCrossroad() {
    var crossroadId = document.querySelector('.markerId');
    $.ajax({
        url: 'crossroads/'+crossroadId.value,
        type: 'DELETE',
        contentType:'application/json',
        dataType:'json'
    });
    generate();
}

var listener = [];
var markers =[];

function findWay() {
    var findWay = document.querySelector('.findWay');
    findWay.style.display = "block";
}

function closeFindWay() {
    var findWay = document.querySelector('.findWay');
    findWay.style.display = "none";
}

function chooseMarker(context, index) {
    for(var i=0; i<allMarkers.length; ++i) {
        listener[i] = allMarkers[i].addListener('click', function() {
            context.value = this.position;
            markers[index] = this;
            for(var j=0; j<allMarkers.length; ++j) {
                google.maps.event.removeListener(listener[j]);
            }
        });
    }
}

var result = null;
function ajaxCallBack(retString){
    result = retString;
    setOnMap();
}

function findMyWay() {
    var vehicleWidth = document.querySelector('.vehicleWidth');
    var vehicleHeight = document.querySelector('.vehicleHeight');
    var data ={
        "start": {
            "id": markers[0].id,
            "longitude": markers[0].position.lat(),
            "latitude": markers[0].position.lng()
        },
        "end": {
            "id": markers[1].id,
            "longitude": markers[1].position.lat(),
            "latitude": markers[1].position.lng()
        },
        "vehicle": {
            "width": vehicleWidth.value,
            "height": vehicleHeight.value
        }
    };
    $.ajax({
        url: 'findRoute',
        type: 'PUT',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json',
        success: function(data) {
            ajaxCallBack(data);
            return data;
        }
    });
}

function setOnMap() {
    console.log(result);
    for(var i=0; i<result.length - 1; ++i) {
        var polylineOptions = {
                path: [{
                    lng: result[i].latitude,
                    lat: result[i].longitude
                },
                {
                    lng: result[i+1].latitude,
                    lat: result[i+1].longitude
                }],
            geodesic: true,
            strokeColor: '#02A310',
            strokeOpacity: 1.0,
            strokeWeight: 13
        }
        var newRoad = new google.maps.Polyline(polylineOptions);
        newRoad.setMap(map);
    };
}

function addMarker() {
    var myLatlng = new google.maps.LatLng(52.587711, 19.669549);
    var markerOptions = {
        position: myLatlng,
        draggable: true
    };
    var newMarker = new google.maps.Marker(markerOptions);
    newMarker.setMap(map);

    var data = {
        "longitude" : markerOptions.position.lat(),
        "latitude" : markerOptions.position.lng()
    };

    $.ajax({
        url: 'crossroads',
        type: 'POST',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
    setTimeout(generate({lat: map.getCenter().lat(), lng: map.getCenter().lng()}, map.getZoom()));
}

function addRoad() {
    var addRoad = document.querySelector('.addRoad');
    addRoad.style.display = "block";
}

function closeAddRoad() {
    var addRoad = document.querySelector('.addRoad');
    addRoad.style.display = "none";
}
function pushAddRoad() {
    console.log(markers[0])
    var roadWidth = document.querySelector('.roadWidth');
    var roadHeight = document.querySelector('.roadHeight');
    var data = {
        "c1": {
            "id": markers[0].id,
            "longitude": markers[0].position.lat(),
            "latitude": markers[0].position.lng()
        },
        "c2": {
            "id": markers[1].id,
            "longitude": markers[1].position.lat(),
            "latitude": markers[1].position.lng()
        },
        "r1": {
            "start": {
                "id": markers[0].id,
                "longitude": markers[0].position.lat(),
                "latitude": markers[0].position.lng()
            },
            "end": {
                "id": markers[1].id,
                "longitude": markers[1].position.lat(),
                "latitude": markers[1].position.lng(),
            },
            "width": roadWidth.value,
            "height": roadHeight.value
        }
    };
    $.ajax({
        url: 'roads',
        type: 'POST',
        contentType:'application/json',
        data: JSON.stringify(data),
        dataType:'json'
    });
    generate({lat: map.getCenter().lat(), lng: map.getCenter().lng()}, map.getZoom());
}