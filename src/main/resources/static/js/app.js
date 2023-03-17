//@author hcadavid

let app = (function (api) {
    let _author = "";
    let _blueprints = [];
    let _publicFunctions = {};
    let _currentBp = {};
    let _isCreating = false;
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
            _currentBp = data;
        });
    }

    let _drawBlueprint = (data) => {
        let points = data.points;
        let ctx = _clearCanvas();
        ctx.moveTo(points[0].x, points[0].y);
        for (let i = 1; i < points.length; i++) {
            const point = points[i];
            ctx.lineTo(point.x, point.y);
        }
        ctx.stroke();
    }

    let _clearCanvas = () => {
        let canvas = $("#blueprints-canvas")[0];
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.beginPath();
        return ctx;
    }
    
    let _newBlueprint = () => {
        _currentBp = {
            author:_author,
            points:[],
            name:""
        };
    }

    let _showDrawing = (data) => {
        $("#blueprint-name").html(data.name);
        $("#canvas-section").removeClass("not-visible");
    }

    let _draw = function (event) {
        let canvas = $("#blueprints-canvas")[0];
        context = canvas.getContext("2d");
        let offset  = _getOffset(canvas);
        _currentBp.points.push({x: event.pageX-offset.left, y: event.pageY-offset.top});
        _renderBlueprint(_currentBp);
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

    let _createBp = () => {
        let newBpName = $("#new-bpname").val();
        _currentBp.name = newBpName;
    }
 
    _publicFunctions.setName = function (newName) {
        _author = newName;
    }

    _publicFunctions.searchBlueprints = function (authorName) {
        this.setName(authorName);
        api.getBlueprintsByAuthor(authorName, _renderSearch);
    }
    _publicFunctions.drawBlueprint = function (authorName, bpName) {
        $("#new-bpname").addClass("remove");
        api.getBlueprintsByNameAndAuthor(authorName, bpName, _renderBlueprint);
    }
    _publicFunctions.init = function() {
        let canvas = $("#blueprints-canvas")[0]; 
        ctx = canvas.getContext("2d");
        if(window.PointerEvent) {
            canvas.addEventListener("pointerdown", _draw, false); 
        } else {
            canvas.addEventListener("mousedown", _draw, false);
        }
    }
    _publicFunctions.updateBlueprint = function() {
        //duda porque funciona
        if(_isCreating) {
            if (!$("#new-bpname").val()) {
                alert("El campo new name debe estar lleno");
                return;
            }
            _isCreating = false;
            _createBp();
            $("#new-bpname").addClass("remove");
            $("#button-delete").prop("disabled", false);
            return api.postBlueprint(_currentBp).then(res => this.searchBlueprints(_currentBp.author));
        }
        return api.putBlueprint(_currentBp).then(res => this.searchBlueprints(_currentBp.author));
    }

    _publicFunctions.createBlueprint = function() {
        $("#new-bpname").removeClass("remove");
        $("#button-delete").prop("disabled", true);
        _isCreating = true;
        _newBlueprint();
        _clearCanvas();
    }

    _publicFunctions.deleteBlueprint = function() {
        api.deleteBlueprint(_currentBp.author, _currentBp.name).then(res => this.searchBlueprints(_currentBp.author));
    }

    return _publicFunctions;

})(apiclient);


/*
Example of use:
var fun=function(list){
	console.info(list);
}
app.setName("svbujsfvbnsj")

apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/