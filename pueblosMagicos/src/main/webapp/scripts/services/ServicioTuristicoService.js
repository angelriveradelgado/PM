'use strict';

App.factory('ServicioTuristicoService', ['$http', '$q', 'CONFIG',  function($http, $q, CONFIG)
{
	return {
        
		getServicioTuristicoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByidPST: function(idPST) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/idpst/' + idPST )
	        .then
	        (
	            function(response)
	            {
	            	console.log('getEstablecimientoByidPST ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while getEstablecimientoByidPST ');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    
	    getAllServiciosTuristicosByEstablecimiento: function(idEstablecimiento) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/Establecimiento/' + idEstablecimiento )
	        .then
	        (
	            function(response)
	            {
	            	console.log('getEstablecimientoByidPST ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while getEstablecimientoByidPST ');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByPMByLimit: function(id, first, numRegistros) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/puebloMagico/' + id + '/limit/' + first + '/' + numRegistros )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching servicios');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByIdEstadoRegistroByLimit: function(id, first, numRegistros) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/estadoRegistro/' + id + '/limit/' + first + '/' + numRegistros )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch st by idregistro ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching st by idregistro');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByPMOrderByPromedioAscByTipoByLimit: function(id, first, numRegistros, idTipo) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristicoOrderByPromedioAsc/puebloMagico/' + id + '/idTipo/' + idTipo + '/limit/' + first + '/' + numRegistros )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByPMOrderByPromedioDescByIdTipoByLimit: function(id, first, numRegistros, idTipo) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristicoOrderByPromedioDesc/puebloMagico/' + id + '/idTipo/' + idTipo + '/limit/' + first + '/' + numRegistros )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByPMOrderByPrecioAscByTipoByLimit: function(id, first, numRegistros, idTipo) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristicoOrderByPrecioAsc/puebloMagico/' + id + '/idTipo/' + idTipo + '/limit/' + first + '/' + numRegistros )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosTuristicosByPMOrderByPrecioDescByTipoByLimit: function(id, first, numRegistros, idTipo) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristicoOrderByPrecioDesc/puebloMagico/' + id + '/idTipo/' + idTipo + '/limit/' + first + '/' + numRegistros )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServicioTuristicoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('getEstablecimientoByidPST ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while getEstablecimientoByidPST ');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServicioTuristicoAventura: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristicoAventura/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('getEstablecimientoByidPST ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while getEstablecimientoByidPST ');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServicioTuristicoAlojamiento: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristicoAlojamiento/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('getEstablecimientoByidPST ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while getEstablecimientoByidPST ');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	     
	    createServicioTuristico: function(servicioTuristico)
	    {
	    	var data = $.param({
	    		idEstablecimiento: servicioTuristico.EIdEstablecimiento,
	    		nombre: servicioTuristico.nombre,
	    		aforo: servicioTuristico.aforo,
	    		idTST: servicioTuristico.tstIdtSt,
	    		precioMinimo: servicioTuristico.precioMinimo,   
	    		precioMaximo: servicioTuristico.precioMaximo,
	    		precioMedio: servicioTuristico.precioMedio,
	    		descripcion: servicioTuristico.descripcion,
	    		sitioWeb: servicioTuristico.sitioWeb,
	    		telefono: servicioTuristico.telefono,
	    		extensionTelefono: servicioTuristico.extensionTelefono
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristico', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        updateServicioTuristico: function(servicioTuristico)
	    {
        	console.log(servicioTuristico);
	    	var data = $.param({
	    		idServicioTuristico: servicioTuristico.idServicioTuristico,
	    		idEstablecimiento: servicioTuristico.eidEstablecimiento,
	    		nombre: servicioTuristico.nombre,
	    		aforo: servicioTuristico.aforo,
	    		idTST: servicioTuristico.tstIdtSt,
	    		precioMinimo: servicioTuristico.precioMinimo,   
	    		precioMaximo: servicioTuristico.precioMaximo,
	    		precioMedio: servicioTuristico.precioMedio,
	    		descripcion: servicioTuristico.descripcion,
	    		sitioWeb: servicioTuristico.sitioWeb,
	    		telefono: servicioTuristico.telefono,
	    		extensionTelefono: servicioTuristico.extensionTelefono
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristicoEdit', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
	    createServicioTuristicoAventura: function(servicioTuristico)
	    {
	    	var data = $.param({
	    		idEstablecimiento: servicioTuristico.EIdEstablecimiento,
	    		nombre: servicioTuristico.nombre,
	    		aforo: servicioTuristico.aforo,
	    		idTST: servicioTuristico.tstIdtSt,
	    		precioMinimo: servicioTuristico.precioMinimo,   
	    		precioMaximo: servicioTuristico.precioMaximo,
	    		precioMedio: servicioTuristico.precioMedio,
	    		descripcion: servicioTuristico.descripcion,
	    		sitioWeb: servicioTuristico.sitioWeb,
	    		telefono: servicioTuristico.telefono,
	    		extensionTelefono: servicioTuristico.extensionTelefono
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristicoAventura', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        updateServicioTuristicoAventura: function(servicioTuristico)
	    {
	    	var data = $.param({
	    		idServicioTuristico: servicioTuristico.idServicioTuristico,
	    		idEstablecimiento: servicioTuristico.EIdEstablecimiento,
	    		nombre: servicioTuristico.nombre,
	    		aforo: servicioTuristico.aforo,
	    		idTST: servicioTuristico.tstIdtSt,
	    		precioMinimo: servicioTuristico.precioMinimo,   
	    		precioMaximo: servicioTuristico.precioMaximo,
	    		precioMedio: servicioTuristico.precioMedio,
	    		descripcion: servicioTuristico.descripcion,
	    		sitioWeb: servicioTuristico.sitioWeb,
	    		telefono: servicioTuristico.telefono,
	    		extensionTelefono: servicioTuristico.extensionTelefono
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristicoAventuraEdit', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        createServicioTuristicoAlojamiento: function(servicioTuristico, servicioTuristicoAlojamiento )
	    {
	    	var data = $.param({
	    		idEstablecimiento: servicioTuristico.EIdEstablecimiento,
	    		nombre: servicioTuristico.nombre,
	    		aforo: servicioTuristico.aforo,
	    		idTST: servicioTuristico.tstIdtSt,
	    		precioMinimo: servicioTuristico.precioMinimo,   
	    		precioMaximo: servicioTuristico.precioMaximo,
	    		precioMedio: servicioTuristico.precioMedio,
	    		descripcion: servicioTuristico.descripcion,
	    		sitioWeb: servicioTuristico.sitioWeb,
	    		telefono: servicioTuristico.telefono,
	    		extensionTelefono: servicioTuristico.extensionTelefono,
	    		
	    		tipoOperacion: servicioTuristicoAlojamiento.tipoOperacion,
	    		tipoAlojamiento: servicioTuristicoAlojamiento.tipoAlojamiento,
	    		tipoServicioAlojamiento: servicioTuristicoAlojamiento.tipoServicioAlojamiento
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristicoAlojamiento', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        updateServicioTuristicoAlojamiento: function(servicioTuristico)
	    {
	    	var data = $.param({
	    		idServicioTuristico: servicioTuristico.idServicioTuristico,
	    		idEstablecimiento: servicioTuristico.EIdEstablecimiento,
	    		nombre: servicioTuristico.nombre,
	    		aforo: servicioTuristico.aforo,
	    		idTST: servicioTuristico.tstIdtSt,
	    		precioMinimo: servicioTuristico.precioMinimo,   
	    		precioMaximo: servicioTuristico.precioMaximo,
	    		precioMedio: servicioTuristico.precioMedio,
	    		descripcion: servicioTuristico.descripcion,
	    		sitioWeb: servicioTuristico.sitioWeb,
	    		telefono: servicioTuristico.telefono,
	    		extensionTelefono: servicioTuristico.extensionTelefono
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristicoAlojamientoEdit', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        deleteServicioTuristicoAventura: function(id)
	    {
	    	var data = $.param({
	    		idServicioTuristico: id
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.delete(CONFIG.urlWebService + '/servicioTuristicoAventuraEdit', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        deleteServicioTuristicoAlojamiento: function(id)
	    {
	    	var data = $.param({
	    		idServicioTuristico: id
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.delete(CONFIG.urlWebService + '/servicioTuristicoAlojamientoEdit', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        getAllFotosByIdServicioTuristico: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/' + id + '/fotos' )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getAtractivoTuristicoFoto: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/foto/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getEvaluacionesByIdServicioTuristicoAndLimit: function( id, first, numReg ) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/' + id + '/evaluacion/first/' + first + '/numReg/' + numReg )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getEvaluacionAventuraByIdEvaluacionAventura: function( id ) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/evaluacionAventura/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getEvaluacionAlojamientoByIdEvaluacionAlojamiento: function( id ) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/evaluacionAlojamiento/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosByidPST: function( id ) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/idpst/' + id )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
        
	    insertEvaluacionServicioTuristicoAventura: function( evaluacion, evaluacionAventura )
	    {
	    	var data = $.param({
	    		comentario: evaluacion.comentario,
	    		idUsuario: evaluacion.TIdUsuario,
	    		idServicioTuristico: evaluacion.sTIdServicioTuristico,
	    		aspectoEstablecimiento: evaluacion.aspectoEstablecimiento,
	    		atencionCliente: evaluacion.atencionCliente,
	    		eficienciaServicio: evaluacion.eficienciaServicio,
	    		higieneEstablecimiento: evaluacion.higieneEstablecimiento,
	    		relacionPrecioCalidad: evaluacion.relacionPrecioCalidad,
	    		accesibilidad: evaluacion.accesibilidad,
	    		comunicacion: evaluacion.comunicacion,
	    		manejoIdiomas: evaluacion.manejoIdiomas,
	    		senalamientoInterno: evaluacion.senalamientoInterno,
	    		senalamientoExterno: evaluacion.senalamientoExterno, 
	    		
	    		equipamientoYMaterial: evaluacionAventura.equipamientoYMaterial,
	    		informacionActividad: evaluacionAventura.informacionActividad,
	    		informacionRiesgos: evaluacionAventura.informacionRiesgos,
	    		condicionEquipo: evaluacionAventura.condicionEquipo,
	    		informacionRequisitos: evaluacionAventura.informacionRequisitos,
	    		servicioMedico: evaluacionAventura.servicioMedico,
	    		seguroVida: evaluacionAventura.seguroVida,
	    		supervision: evaluacionAventura.supervision,
	    		asistencia: evaluacionAventura.asistencia,
	    		informacionReservaLugar: evaluacionAventura.informacionReservaLugar,
	    		acuerdoRiesgos: evaluacionAventura.acuerdoRiesgos
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioAventura/evaluacion', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        insertEvaluacionServicioTuristicoAlojamiento: function( evaluacion, evaluacionAlojamiento )
	    {
	    	var data = $.param({
	    		comentario: evaluacion.comentario,
	    		idUsuario: evaluacion.TIdUsuario,
	    		idServicioTuristico: evaluacion.sTIdServicioTuristico,
	    		aspectoEstablecimiento: evaluacion.aspectoEstablecimiento,
	    		atencionCliente: evaluacion.atencionCliente,
	    		eficienciaServicio: evaluacion.eficienciaServicio,
	    		higieneEstablecimiento: evaluacion.higieneEstablecimiento,
	    		relacionPrecioCalidad: evaluacion.relacionPrecioCalidad,
	    		accesibilidad: evaluacion.accesibilidad,
	    		comunicacion: evaluacion.comunicacion,
	    		manejoIdiomas: evaluacion.manejoIdiomas,
	    		senalamientoInterno: evaluacion.senalamientoInterno,
	    		senalamientoExterno: evaluacion.senalamientoExterno,
	    		
	    		instalacionesRecepcion: evaluacionAlojamiento.instalacionesRecepcion,
	    		servicioPortero: evaluacionAlojamiento.servicioPortero,
	    		servicioMaletero: evaluacionAlojamiento.servicioMaletero,
	    		registroEntrada: evaluacionAlojamiento.registroEntrada,
	    		iluminacionHabitacion: evaluacionAlojamiento.iluminacionHabitacion,
	    		confortCama: evaluacionAlojamiento.confortCama,
	    		limpiezaBano: evaluacionAlojamiento.limpiezaBano,
	    		mobiliario: evaluacionAlojamiento.mobiliario,
	    		equipamientoElectronicos: evaluacionAlojamiento.equipamientoElectronicos,
	    		servicioLavadoPlanchado: evaluacionAlojamiento.servicioLavadoPlanchado
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioAlojamiento/evaluacion', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
    
        
        ////////////////////
        /////////////Catalogos
        ////////////////////
        
        getTiposServiciosTuristicos: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tiposServiciosTuristicos' )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetchServicioTuristico ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching estados');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getTipoServicioTuristicoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tipos/idTipoServicioTuristico/' + id  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch tipo servicio ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching tipo servicio');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getFormasPago: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/formaPago'  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formaspago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching formas pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getFormaPagoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/formaPago/idFormaPago/' + id  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    setFormaPago: function(idServicio, idFormaPago)
	    {
	    	var data = $.param({
	    		stIdServicio: idServicio,
	    		fpIdformaPago: idFormaPago
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristico/formaPago', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        unsetFormaPago: function(idServicio, idFormaPago)
	    {
	    	var data = $.param({
	    		stIdServicio: idServicio,
	    		fpIdformaPago: idFormaPago
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.delete(CONFIG.urlWebService + '/servicioTuristico/formaPago', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        getTiposServiciosTuristicosAlojamiento: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tiposAlojamiento'  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getTipoServicioTuristicoAlojamientoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tiposAlojamiento/idTipoAlojamiento/' + id  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    getServiciosAdicionalesAlojamiento: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/servicioAdicionalAlojamiento'  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
         
	    getServiciosAdicionalesAlojamientoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/servicioAdicionalAlojamiento/idServicioAdicionalAlojamiento/' + id  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
	    
	    setServicioAdicionalAlojamiento: function(idServicio, idServicioAdicional)
	    {
	    	var data = $.param({
	    		saIdservicioAdicional: idServicioAdicional,
	    		staSTIdServicio: idServicio
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.post(CONFIG.urlWebService + '/servicioTuristico/servicioAdicionalAlojamiento', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        unsetServicioAdicionalAlojamiento: function(idServicio, idServicioAdicional)
	    {
	    	var data = $.param({
	    		saIdservicioAdicional: idServicioAdicional,
	    		staSTIdServicio: idServicio
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.delete(CONFIG.urlWebService + '/servicioTuristico/servicioAdicionalAlojamiento', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        getTipoHabitacionAlojamiento: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tipoHabitacion'  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
        
	    getTipoHabitacionAlojamientoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tipoHabitacion/idTipoHabitacion/' + id  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
        
	    setTipoHabitacionAlojamiento: function(idServicio, IdTipoHabitacion)
	    {
	    	var data = $.param({
	    		IdTipoHabitacion: IdTipoHabitacion,
	    		staSTIdServicio: idServicio
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			 return $http.post(CONFIG.urlWebService + '/servicioTuristico/servicioAdicionalAlojamiento', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        unsetTipoHabitacionAlojamiento: function(idServicio, IdTipoHabitacion)
	    {
	    	var data = $.param({
	    		IdTipoHabitacion: IdTipoHabitacion,
	    		staSTIdServicio: idServicio
	    	});
	    	
	    	console.log(data);
			var config = 
			{
	            headers : 
	            {
	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	            }
	        }
			
			return $http.delete(CONFIG.urlWebService + '/servicioTuristico/servicioAdicionalAlojamiento', data, config)
	        .success(function (data, status, headers, config) {
	            return data;
	        })
	        .error(function (data, status, header, config) {
	            return "Data: " + data +
	                "<hr />status: " + status +
	                "<hr />headers: " + header +
	                "<hr />config: " + config;
	        });
        },
        
        getTipoOperacionAlojamiento: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tipoOperacionAlojamiento'  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
        
	    getTipoOperacionAlojamientoById: function(id) 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tipoOperacionAlojamiento/idTipoOperacionAlojamiento/' + id  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    },
        
	    getTiposAlojamientos: function() 
	    {
	        return $http.get( CONFIG.urlWebService + '/servicioTuristico/tipoOperacionAlojamiento'  )
	        .then
	        (
	            function(response)
	            {
	            	console.log('fetch formapago ok ');
	                return response.data;
	            },  
	            function(errResponse)
	            {
	                console.error('Error while fetching forma pago');
	                return $q.reject(errResponse);
	            }
	        );
	    }, 
	    
	    deleteFotoAtractivoTuristico: function( idfoto)
	 	  {
	            return $http.delete( CONFIG.urlWebService + '/servicioTuristico/eliminarFoto/' + idfoto)
	            .then(
	                function(response)
	                {
	                    return response.data;
	                }, 
	                function(errResponse)
	                {
	                    console.error('Error while delete pm');
	                    return $q.reject(errResponse);
	                }
	            );
	       }
	    
	    
    }; 
 
}]);