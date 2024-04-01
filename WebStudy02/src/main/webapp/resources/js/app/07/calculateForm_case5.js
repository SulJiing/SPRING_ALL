document.addEventListener("DOMContentLoaded", () => {
   calForm.addEventListener("submit", (event) => {
      event.preventDefault();
      let form = event.target;
      let url = form.action;
      let method = form.method;
      
      let contentType = "application/json" //1
      
      let accept = "application/json";
      let formData = new FormData(form);
      let nativeData = {
         leftOp : parseFloat(formData.get("left")),
         rightOp : parseFloat(formData.get("right")),
         operatorType : formData.get("operator")
      };
      let jsonStr = JSON.stringify(nativeData)//marshalling , unmarshalling = .parses
      
      let fetchPromise = fetch(url, {
         method: method,
         headers: {
            "Content-Type": contentType,
            "Accept": accept
         }, body: jsonStr // 2
      });
      fetchPromise.then(resp => {
         if (resp.ok) {
            return resp.json();
         } else {
            throw new Error(`상태코드 : ${resp.status} 발생`, { cause: resp });
         }
      }).then(jsonObj => {
         resultArea.innerHTML = jsonObj.expression;

      }).catch(err => {
         console.log(err.message);
         if (err.cause) {
            let resp = err.cause;
            resp.text().then(ep => resultArea.innerHTML = ep);
         }
      });
      return false;
   });
});