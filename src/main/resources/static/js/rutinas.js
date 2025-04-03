/* La siguiente función se utiliza para visualizar la imagen seleccionada en la
 * página html donde se desea "cargar" utilizando un llamado "ajax"*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                    .attr('src', e.target.result)
                    .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCard(formulario) {
    var valor = formulario.elements[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    $("#resultsBlock").load(url);
}


function calcularCuota() {
    const monto = parseFloat(document.getElementById('calculoMonto').value);
    const plazo = parseInt(document.getElementById('calculoPlazo').value);
    const tasa = parseFloat(document.getElementById('calculoTasa').value);

    if (isNaN(monto) || isNaN(plazo) || isNaN(tasa) || plazo <= 0) {
        document.getElementById('resultadoCuota').innerText = "Ingrese valores válidos.";
        return;
    }

    const interes = monto * (tasa / 100); // interés total anual
    const totalPagar = monto + interes;
    const cuota = totalPagar / plazo;

    document.getElementById('resultadoCuota').innerText = cuota.toLocaleString('es-CR', {
        style: 'currency',
        currency: 'CRC',
        minimumFractionDigits: 2
    });
    
}
