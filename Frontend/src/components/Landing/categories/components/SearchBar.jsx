
import { styled , InputBase}  from '@mui/material'

const SearchBar = () => {
  
  const Search = styled('div')(() => ({
    position: 'relative',
    borderRadius: '100px',
    backgroundColor:'#ffffff',     
    border:'2px solid #025373',
    width: '100%',
  }));
  
  
  const StyledInputBase = styled(InputBase)(() => ({
    width:'100%',
    color:"primary",
    fontSize:'25px',
    margin:'5px 5px 5px 5px'
    
  }));
  
  return (
    
    <Search>
    <StyledInputBase
      placeholder="Buscar fotografia..."
      inputProps={{ 'aria-label': 'search' }}
    />
      </Search>
  )
}

export default SearchBar