<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src='${pageContext.request.contextPath }/resources/lib/fullcalendar-6.1.10/dist/index.global.min.js'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth'
      , initialDate:'2005-01-01'
      , events: '${pageContext.request.contextPath }/fullcalendar/case2/json'
      , eventClick: function(info) {
    	    console.log('Event: ' + info.event.title);
    	    console.log('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
    	    console.log('View: ' + info.view.type);
    	    info.el.style.borderColor = 'red';
    	    info.jsEvent.preventDefault();
    	    location.href = `${pageContext.request.contextPath}\${info.event.url}`;   
    	}
    });
    calendar.render();
  });
</script>
<pre>
	설치 : https://fullcalendar.io/docs/initialize-globals
 	event 객체 구조 : https://fullcalendar.io/docs/event-object
 	event 소스 종류 : https://fullcalendar.io/docs/event-source
 	event from json : https://fullcalendar.io/docs/events-json-feed
 	event 클릭 uievent 처리 : https://fullcalendar.io/docs/eventClick
</pre>
<div id='calendar' class="w-50"></div>
