import
  { 
    CardMedia,
    Grid,
    Card,
    Modal
  } from '@mui/material'
import * as React from 'react'
import CardBuy from './CardBuy';
import useAxios from '../../../hooks/useAxios'
import axios from 'axios';
import apiClient from '../../../services/apiRest'




const CardPhoto = () => { 


  const [ open, setOpen ] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <>
      { loading && <div>Loading</div>}
      { !loading &&(
      <Grid container spacing={4}>
      { photo.map( item  => (

        <Grid
        key={ id }
        item xs={12} sm={6} md={4}>
          <Card onClick={handleOpen} sx={{ width: '100%', height: '100%', display: 'flex', flexDirection: 'column', boxShadow: 'none', }}>
            <CardMedia
              sx={{
                margin: '5% 5% 5% 5%',
                paddign: '10% 10% 10% 10%'
              }}
              component="img"
              height="300"
              image={ item.url }
              alt="photo"
              id={ item.id }
              />
          </Card>
        </Grid>
        ))}
        <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        >
      <CardBuy/>
    </Modal>
    </Grid>
    )}
    </>
    
    )}


export default CardPhoto;