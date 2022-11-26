import * as Yup from 'yup';


let validateString =/^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/;
let validateAlphanumeric = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
let mailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

export const ValidateSchema = Yup.object().shape({
  name: Yup.string()
    .required('Debes ingresar un nombre')
    .matches( validateString, {
      excludeEmptyString: true,
      message:'El nombre debe ser solo texto'
    })
    .min(2,'Debe ingresar al menos 2 letras.')
    .max(25,'El nombre es muy extenso.'),

  surname: Yup.string()
      .required('Debe ingresar un apellido.')
      .matches( validateString, {
        excludeEmptyString: true,
        message:'El apellido debe ser solo texto.'
      })
      .min(2,'Debe ingresar al menos 2 letras.')
      .max(30, 'El apellido es muy extenso'),
  mail: Yup.string( )
        .matches( mailformat, {
          excludeEmptyString: true,
          message:'Ingrese un email valido'
        })
        .required('El email es requerido.')
        .email('El email ingresado no es valido.')
        .min( 6, 'EL mail es demaciado corto.')
        .max(50,'El email ingresado es muy largo.'),
  password: Yup.string()
        .matches( validateAlphanumeric,{
          excludeEmptyString: true,
          message: 
          'La constraseña debe tener al menos una mayúscula, una minúscula y un numero.'
        })
        .required('Debe ingresar una contraseña')
        .min(6,'Debe ingresar minimo 6 caracteres')
        .max(10,'La contraseña no debe de mas de 10 caracteres.'),
  passwordConfirm: Yup.string().when('password', {
    is:( value ) => ( value && value.length > 0 ? true : false ),
    then: Yup.string()
        .oneOf([Yup.ref('password')],'Las contraseñas no coinciden.')
        .required('Debes repetir la constraseña.')
  })
}); 

