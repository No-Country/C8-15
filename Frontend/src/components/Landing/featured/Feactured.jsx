
import { Typography, Grid } from '@mui/material'
import CardProfile from './card'



const Feactured = () =>
{
  return (
    <>
      <Grid >
      <Typography 
      color='#0000000'
      fontSize={'40px'}
      sx={{
        margin:'20px 20px 20px 20px',
        padding:'20px 20px 20px 20px',
      }}>
        Profesionales sugeridos
      </Typography>
      <Grid container
        direction="row"
        justifyContent="space-around"
        alignItems="center"
        spacing={2}
        col={1}
        sx={{
          marginButton:'10%',
          paddingBottom:'5%',
        }}
        >
        <CardProfile />
      </Grid>
      </Grid>
    </>
  )
}

export default Feactured;