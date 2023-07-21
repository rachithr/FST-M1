using System;
using System.ComponentModel.DataAnnotations;

public class EnumValidationAttribute : ValidationAttribute
{
    private readonly Type _enumType;

    public EnumValidationAttribute(Type enumType)
    {
        _enumType = enumType;
    }

    public override bool IsValid(object value)
    {
        if (value == null || !_enumType.IsEnum)
        {
            return false;
        }

        if (value is Enum enumValue)
        {
            return Enum.IsDefined(_enumType, enumValue);
        }

        return false;
    }
}
[ApiController]
[Route("api/[controller]")]
public class MyController : ControllerBase
{
    [HttpPost]
    public IActionResult MyAction([FromBody] MyRequestDTO request)
    {
        if (!ModelState.IsValid)
        {
            var errors = ModelState.Values
                .SelectMany(v => v.Errors.Select(e => e.ErrorMessage))
                .ToList();

            return BadRequest(errors);
        }

        // Your API logic here
    }
}
