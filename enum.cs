using System;
using System.Text.Json;
using System.Text.Json.Serialization;

public class StatusEnumConverter : JsonConverter<Status>
{
    public override Status Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        var enumName = reader.GetString();
        if (Enum.TryParse<Status>(enumName, ignoreCase: true, out var enumValue))
        {
            return enumValue;
        }

        return Status.None; // Or any default value for invalid cases
    }

    public override void Write(Utf8JsonWriter writer, Status value, JsonSerializerOptions options)
    {
        writer.WriteStringValue(value.ToString());
    }
}
public void ConfigureServices(IServiceCollection services)
{
    // Other configurations

    services.AddControllers()
            .AddJsonOptions(options =>
            {
                options.JsonSerializerOptions.Converters.Add(new StatusEnumConverter());
            });
}
