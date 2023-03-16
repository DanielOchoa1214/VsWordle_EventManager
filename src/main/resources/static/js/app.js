//@author hcadavid

let app = (function (api) {
    let _author = "";
    let _blueprints = [];
    let publicFunctions = {};
    let currentBp = {};
    //let canvas = $("#blueprints-canvas")[0];

    let _renderSearch = (data) => {
        $(document).ready(() => {
            objData = _dataToObject(data);
            _renderTable(objData, data);
            _calculateTotalPoints(objData);
            _showResults();
            _blueprints = objData;
        });
    }

    let _dataToObject = (data) => {
        let obj = data.map((x) => (
            {
                name: x.name,
                pointsNum : x.points.length
            }
        ));
        return obj;
    }

    let _renderTable = (objData, data) => {
        $("#author").text(`${data[0].author}'s blueprints`);
        $("#blueprints tbody").text("");
        objData.map((x) => {
            let markup = `
            <tr>
                <td>${x.name}</td>
                <td>${x.pointsNum}</td>
                <td><button class="btn btn-success" onclick="app.drawBlueprint('${data[0].author}', '${x.name}')">Open</button></td>
            </tr>`;
            $("#blueprints tbody").append(markup);
        });
    }

    let _calculateTotalPoints = (objData) => {
        let totalPoints = objData.reduce((total, i) => total + i.pointsNum, 0);
        $("#total-points").html(totalPoints);
    }

    let _showResults = () => {
        $("#successful-search").removeClass("remove");
        $("#search-error").addClass("remove");
        $("#search-section").removeClass("not-visible");
        $("#canvas-section").addClass("not-visible");
    }

    let _renderBlueprint = (data) => {
        $(document).ready(() => {
            _drawBlueprint(data);
            _showDrawing(data);
            currentBp = data;
        });
    }

    let _drawBlueprint = (data) => {
        let points = data.points;
        let canvas = $("#blueprints-canvas")[0];
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.beginPath();
        ctx.moveTo(points[0].x, points[0].y);
        for (let i = 1; i < points.length; i++) {
            const point = points[i];
            ctx.lineTo(point.x, point.y);
        }
        ctx.stroke();
    }

    let _showDrawing = (data) => {
        $("#blueprint-name").html(data.name);
        $("#canvas-section").removeClass("not-visible");
    }

    let _draw = function (event) {
        let canvas = $("#blueprints-canvas")[0];
        context = canvas.getContext("2d");
        let offset  = _getOffset(canvas);
        //context.fillRect(event.pageX-offset.left, event.pageY-offset.top, 5, 5);
        currentBp.points.push({x: event.pageX-offset.left, y: event.pageY-offset.top});
        _renderBlueprint(currentBp);
    }

    let _getOffset = function (obj) {
        var offsetLeft = 0;
        var offsetTop = 0;
        do {
          if (!isNaN(obj.offsetLeft)) {
              offsetLeft += obj.offsetLeft;
          }
          if (!isNaN(obj.offsetTop)) {
              offsetTop += obj.offsetTop;
          }   
        } while(obj = obj.offsetParent );
        return {left: offsetLeft, top: offsetTop};
    } 
 
    publicFunctions.setName = function (newName) {
        _author = newName;
    }

    publicFunctions.updateBlueprints = function (authorName) {
        api.getBlueprintsByAuthor(authorName, _renderSearch);
    }
    publicFunctions.drawBlueprint = function (authorName, bpName) {
        api.getBlueprintsByNameAndAuthor(authorName, bpName, _renderBlueprint);
    }
    publicFunctions.init = function() {
        let canvas = $("#blueprints-canvas")[0]; 
        ctx = canvas.getContext("2d");
        if(window.PointerEvent) {
            canvas.addEventListener("pointerdown", _draw, false); 
        } else {
            canvas.addEventListener("mousedown", _draw, false);
        }
    }

    return publicFunctions;

})(apimock);


/*
Example of use:
var fun=function(list){
	console.info(list);
}
app.setName("svbujsfvbnsj")

apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/