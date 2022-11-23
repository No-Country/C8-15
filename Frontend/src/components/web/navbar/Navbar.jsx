import * as React from 'react';
import { styled, alpha } from '@mui/material/styles';
import { AppBar, 
  Toolbar,
  Typography,
  Container,
  InputBase,
  IconButton,
  Menu,
  MenuItem, 
  Box,
  Button
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search'
import MenuIcon from '@mui/icons-material/Menu'
import theme from '../../../themeConfig'
import Logo  from '../../../assets/pngwing.com.png'



const Navbar = () => {

  const Search = styled('div')(({ theme }) => ({
  position: 'relative',
  borderRadius:'100px',
  border:'0.5px solid  #000000 ',
  backgroundColor: alpha(theme.palette.common.white, 0.7),
  '&:hover': {
    backgroundColor: alpha(theme.palette.common.white, 0.7),
  },
  marginLeft: 0,
  width: '100%',
  [theme.breakpoints.up('sm')]: {
    marginLeft: theme.spacing(1),
    width: 'auto',
  },
}));

const SearchIconWrapper = styled('div')(({ theme }) => ({
  padding: theme.spacing(0, 2),
  color:'#000000',
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: '#000000',
  '& .MuiInputBase-input': {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}));

  const pages = ['Acerca de','Categorias', 'Ingresar', 'Registrarse'];

    const [anchorElNav, setAnchorElNav] = React.useState(null);
  
    const handleOpenNavMenu = (event) => {
      setAnchorElNav(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
      setAnchorElNav(null);
    };
  

    return (
      <>
      <AppBar  position='fixed'theme={theme} 
      sx={{
        backgroundColor:'rgb( 255, 255,255 ,.5)'
      }}>
      <Container maxwidth="xl"   
      >
        <Toolbar disableGutters  >
          <IconButton href="/" >
          <img src={Logo} width='35px' alt="logo" 
          sx={{
            opacity:'0.5'
          }}/>
          </IconButton>
          {/* <Typography
            variant="h6"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'Nunito',
              fontWeight: 700,
              letterSpacing: '.1rem',
              color: '#000000',
              textDecoration: 'none',
            }}
          >
            ViewFinder
          </Typography>
          <Typography
            variant="h5"
            noWrap
            component="a"
            href=""
            sx={{
              mr: 2,
              display: { xs: 'flex', md: 'none' },
              flexGrow: 1,
              fontFamily: 'Nunito',
              fontWeight: 700,
              letterSpacing: '.1rem',
              color: '#000000',
              textDecoration: 'none',
            }}
            >
            ViewFinder
          </Typography> */}
          <Search>
          <SearchIconWrapper>
                <SearchIcon />
              </SearchIconWrapper>
              <StyledInputBase
                placeholder="Buscar…"
                inputProps={{ 'aria-label': 'search' }}
              />
          </Search>
          <Box
          sx={{ 
          flexGrow:6,
          display: { xs: 'none', md: 'flex' } ,
          justifyContent: 'flex-end' }}>
            {pages.map((page) => (
              <Button
              key={page}
              onClick={handleCloseNavMenu}
              sx={{ my: 2, color: '#000000', display: 'block', padding:'1px 20px' }}
              >
                {page}
              </Button>
            ))}
          </Box>
              <Box sx={{ flexGrow:1, display: { xs: 'flex', md: 'none' } , justifyContent: 'flex-end'}}>
                <IconButton
                  size="large"
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleOpenNavMenu}
                  color="#000000"
                >
                  <MenuIcon />
                </IconButton>
                <Menu
                  id="menu-appbar"
                  anchorEl={anchorElNav}
                  anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={Boolean(anchorElNav)}
                  onClose={handleCloseNavMenu}
                  sx={{
                    display: { xs: 'block', md: 'none' },
                  }}
                >
                  {pages.map((page) => (
                    <MenuItem key={page} onClick={handleCloseNavMenu} >
                      <Typography textAlign="center" color="#000000"  >{page}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
        </Toolbar>
      </Container>
    </AppBar>
    </>
    );
  };

export default Navbar;