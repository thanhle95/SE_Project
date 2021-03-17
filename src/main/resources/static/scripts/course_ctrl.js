window.onload = function(){
    let anchors = document.querySelectorAll("table a");
    for(let a of anchors){
        a.onmouseover = courseLinkOnHoverHandler;
        a.onmouseout = courseLinkOnMouseupHandler;
    }
}

function courseLinkOnHoverHandler(e){
    // <div id="divtoshow" style="position: fixed;display:none;">test</div>
    let request = "course-info/" + this.dataset.courseid;
    fetch(request)
        .then(response => response.json())
        .then(data => displayProductInfo(e,data));
}

function displayProductInfo(e,data){
    var left  = e.clientX  + "px";
    var top  = e.clientY  + "px";

    var div = document.getElementById("div_course_info");


    div.innerHTML = `
       <p>
          <strong>${data.courseName}(${data.courseCode}):</strong>
       </p>
       <p>
          ${data.courseDescription}
       </p>`;

    div.style.left = left;
    div.style.top = top;

    div.style.display = "";
    return false;
}

function courseLinkOnMouseupHandler(e,divid){
    var left  = e.clientX  + "px";
    var top  = e.clientY  + "px";

    var div = document.getElementById("div_course_info");

    div.style.left = left;
    div.style.top = top;
    div.style.display = "none";
}

